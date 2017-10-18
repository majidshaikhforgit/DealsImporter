/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.importer;

import com.main.dao.entity.CurrencyAccumulativeCount;
import com.main.dao.entity.InvalidDeal;
import com.main.dao.entity.ValidDeal;
import com.main.dao.jpa.CurrencyAccumulativeCountRepository;
import com.main.dao.jpa.InvalidDealRepository;
import com.main.dao.jpa.ValidDealRepository;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import com.main.service.DealsImporterService;
import com.main.service.DealsImporterServiceImpl;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 *
 * @author Majid
 */
@Slf4j
@RunWith(SpringRunner.class)
public class DealsImporterApplicationTest {

  @TestConfiguration
  static class DealsImporterServiceImplTestConfiguration {

    @Bean
    public DealsImporterService dealImporterService() {
      return new DealsImporterServiceImpl();
    }
  }

  @Autowired
  private DealsImporterService dealImporterService;
  
  @MockBean
  private ValidDealRepository validDealRepository;

  @MockBean
  private InvalidDealRepository invalidDealRepository;

  @MockBean
  private CurrencyAccumulativeCountRepository accumulativeCountRepository;

  @Before
  public void setUp() {
    DealsImporterServiceImpl dis = Mockito.mock(DealsImporterServiceImpl.class);
    List<ValidDeal> validDeals = new ArrayList<ValidDeal>();
    List<InvalidDeal> invalidDeals = new ArrayList<InvalidDeal>();
    List<Object[]> counts = new ArrayList<Object[]>();
    List<CurrencyAccumulativeCount> currencyCountList = new ArrayList<CurrencyAccumulativeCount>();
    ValidDeal validDeal = new ValidDeal(1, "INR", "Pounds", "10-10-2017", "100", "deals.csv");
    InvalidDeal invalidDeal = new InvalidDeal(1, "INR", "Pounds", "10-10-2017", "100", "deals.csv");
    counts.add(new Object[]{"1", "INR"});
    counts.add(new Object[]{"2", "Dollar"});
    validDeals.add(validDeal);
    invalidDeals.add(invalidDeal);
    currencyCountList.add(new CurrencyAccumulativeCount(new Object[]{"1", "INR"}));

    Mockito.when(validDealRepository.save(validDeals)).thenReturn(validDeals);
    Mockito.when(validDealRepository.getFromCurrencyAccumulativeCount()).thenReturn(counts);
    Mockito.when(validDealRepository.countByfilename("deals.csv")).thenReturn(10l);

    Mockito.when(invalidDealRepository.countByfilename("deals.csv")).thenReturn(20l);
    Mockito.when(invalidDealRepository.save(invalidDeals)).thenReturn(invalidDeals);

    Mockito.when(accumulativeCountRepository.save(currencyCountList)).thenReturn(currencyCountList);

  }

  @Test
  public void whenProcessInputFile_thenFileNotFound() {
    String inputFilePath = "/some/unexistingpath";
    String fileName = "deals.csv";
    Assert.assertFalse(dealImporterService.processInputFile(inputFilePath, fileName));
    log.debug("File does not exists : " + fileName);

  }

  @Test
  public void whenProcessInputFileFound_thenRecordsShouldBeInsertedIntoDataBase() {
    String fileName = "deals.csv";
    String workingDir = System.getProperty("user.dir");
    String storePath = workingDir + "/uploads/";
    Assert.assertTrue(dealImporterService.processInputFile(storePath, fileName));
    log.debug("File processed successfully : " + fileName);

  }

  @Test
  public void whenEnquireByfileNameFound_thenPrintTheValidAndInvalidDealsCount() {
    String fileName = "deals.csv";
    String result = dealImporterService.enquireByFileName(fileName);
    Assert.assertNotNull(result);
    log.debug(result);
  }

  @Test
  public void whenEnquireByfileNameNotFound_thenPrintTheValidAndInvalidDealsCount() {
    String fileName = "unkownfile";
    String result = dealImporterService.enquireByFileName(fileName);
    Assert.assertNotNull(result);
    log.debug(result);
  }
}
