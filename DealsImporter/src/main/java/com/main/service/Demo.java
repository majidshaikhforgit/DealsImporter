package com.main.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Demo {

  public static void main(String args[]) {
    long start = System.currentTimeMillis();
    try {
      String workingDir = System.getProperty("user.dir");
      System.out.println(workingDir);
      System.out.println("Start Time :" + start);
//      for (int i = 1; i < 100001; i++) {
//        System.out.println(i+",INR,Dollar,1-10-2017,"+i);
//      }
//      DealsImporterServiceImpl obj = new DealsImporterServiceImpl();
//      String inputFilePath = "/home/webwerks/Documents/cvs/deals.csv";
//      obj.processInputFile(inputFilePath);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      System.out.println(System.currentTimeMillis());
      System.out.println("Time taken :" + (System.currentTimeMillis() - start));
    }
  }

//  public void useCompletableFutureWithExecutor(final List<Deal> tasks) {
//    long start = System.nanoTime();
//    ExecutorService executor = Executors.newFixedThreadPool(Math.min(tasks.size(), 10));
//    List<CompletableFuture<Deal>> futures
//            = tasks.stream()
//            .map(t -> CompletableFuture.supplyAsync(() -> this.save(t), executor))
//            .collect(Collectors.toList());
//
////    List<Deal> result
////            = futures.stream()
////            .map(CompletableFuture::join)
////            .collect(Collectors.toList());
//    long duration = (System.nanoTime() - start) / 1_000_000;
//    System.out.printf("Processed %d tasks in %d millis\n", tasks.size(), duration);
////  System.out.println(result);
//    executor.shutdown();
//  }
//  public Deal save(Deal d) {
////    System.out.println("Deal no : " + d.getId());
//    return d;
//  }
}
