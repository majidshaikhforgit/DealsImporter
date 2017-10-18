package com.main.service;

public interface DealsImporterService {

  boolean processInputFile(String inputFilePath, String fileName);

  String enquireByFileName(String fileName);

}
