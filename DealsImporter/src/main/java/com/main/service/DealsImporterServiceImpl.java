package com.main.service;

import com.main.dao.entity.CurrencyAccumulativeCount;
import com.main.dao.dto.Deal;
import com.main.dao.entity.InvalidDeal;
import com.main.dao.entity.ValidDeal;
import com.main.dao.jpa.CurrencyAccumulativeCountRepository;
import com.main.dao.jpa.InvalidDealRepository;
import com.main.dao.jpa.ValidDealRepository;
import com.main.mapper.DealsMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DealsImporterServiceImpl implements DealsImporterService, DealsMapper {

  @Autowired
  private ValidDealRepository validDealRepository;

  @Autowired
  private InvalidDealRepository invalidDealRepository;

  @Autowired
  private CurrencyAccumulativeCountRepository accumulativeCountRepository;

  public static String fileName;

  long counter = 1;
  long start;

  @Override
  public boolean processInputFile(String inputFilePath, String fileName) {
    setFileName(fileName);
    start = System.currentTimeMillis();
    try {

      do {
        if (!Files.exists(Paths.get(inputFilePath + fileName), new LinkOption[]{LinkOption.NOFOLLOW_LINKS})) {
          return false;
        }
        useCompletableFutureWithExecutor(Files.lines(Paths.get(inputFilePath + fileName)).skip(counter).limit(10000)
                .map(DealsMapper::createDeal)
                .collect(Collectors.toList()));
        counter += 10000;
      } while (counter <= Files.lines(Paths.get(inputFilePath + fileName)).count());
      return true;
    } catch (IOException ex) {
      Logger.getLogger(DealsImporterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }

  }

  public void useCompletableFutureWithExecutor(final List<Deal> tasks) {
    if (tasks == null || tasks.size() <= 0) {
      return;
    }
    long start = System.nanoTime();
    ExecutorService executor = Executors.newFixedThreadPool(Math.min(tasks.size(), 10));

    List<ValidDeal> validDeals = tasks.stream().filter((task) -> task.isValid()).map(DealsMapper::mapToValidDeal).collect(Collectors.toList());
    List<InvalidDeal> invalidDeals = tasks.stream().filter((task) -> !task.isValid()).map(DealsMapper::mapToInvalidDeal).collect(Collectors.toList());
    if (validDeals != null && validDeals.size() > 0) {
      CompletableFuture.supplyAsync(() -> validDealRepository.save(validDeals), executor).thenAccept((d) -> {
        System.out.println("Deals " + d);
        updateAccumulativeCount();
      });
    }
    if (invalidDeals != null && invalidDeals.size() > 0) {
      CompletableFuture.supplyAsync(() -> invalidDealRepository.save(invalidDeals), executor).thenAccept((d) -> {
        System.out.println("Deals " + d);
        updateAccumulativeCount();
      });
    }
    long duration = (System.nanoTime() - start) / 1_000_000;
    System.out.printf("Processed %d tasks in %d millis\n", tasks.size(), duration);
    executor.shutdown();
  }

  public void updateAccumulativeCount() {
    List<CurrencyAccumulativeCount> currencyCountList = validDealRepository.getFromCurrencyAccumulativeCount().stream().map((r) -> new CurrencyAccumulativeCount(r)).collect(Collectors.toList());
    accumulativeCountRepository.save(currencyCountList);
    System.out.println(System.currentTimeMillis());
    System.out.println("Time taken :" + (System.currentTimeMillis() - start));
  }

  @Override
  public String enquireByFileName(String fileName) {
    long countValid = validDealRepository.countByfilename(fileName);
    long countInvalid = invalidDealRepository.countByfilename(fileName);
    return countValid > 0 && countInvalid > 0 ? "Valid Count : " + countValid + " Invalid Count : " + countInvalid : "File Does Not Exists!";
  }

  public static void setFileName(String fileName) {
    DealsImporterServiceImpl.fileName = fileName;
  }

}
