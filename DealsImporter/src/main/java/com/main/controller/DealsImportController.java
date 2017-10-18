package com.main.controller;

import com.main.service.DealsImporterService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
public class DealsImportController {

  @Autowired
  private DealsImporterService importerService;

  @RequestMapping(value = "/importdeals")
  public String home() {
    return "home";
  }

  @RequestMapping(value = "/enquirefile")
  public String enquire() {
    return "enquire";
  }

  @RequestMapping(value = "/enquire", method = RequestMethod.POST)
  public @ResponseBody
  String enquire(@RequestParam(name = "name") String fileName) {
    return importerService.enquireByFileName(fileName);
  }

  @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
  public @ResponseBody
  String uploadFile(@RequestParam("file") MultipartFile file) {
    if (!file.isEmpty() && file.getOriginalFilename().endsWith(".csv")) {
      log.info("File Name " + file.getOriginalFilename());
      try {
        String workingDir = System.getProperty("user.dir");
        String storePath = workingDir + "/uploads/";
        Path rootLocation = Paths.get(storePath);
        if (!Files.exists(rootLocation, new LinkOption[]{LinkOption.NOFOLLOW_LINKS})) {
          Files.createDirectory(rootLocation);
          log.info("Directory is created!");
        } else {
          log.info("Directory Existing!");
        }

        File csvfile = new File(storePath + file.getOriginalFilename());

        if (!csvfile.exists()) {
          Files.copy(file.getInputStream(), rootLocation.resolve(file.getOriginalFilename()));
          log.info("File Created! " + storePath + file.getOriginalFilename());
          CompletableFuture.supplyAsync(() -> importerService.processInputFile(storePath, file.getOriginalFilename())).thenAccept((result) -> {
            System.out.println("Process Completed : " + result);
          });
          return "File is processing please check the logs!!";
        } else {
          log.info("File Already Existing!");
          return "File Already Exists!";
        }

      } catch (IllegalStateException ex) {
        Logger.getLogger(DealsImportController.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
        Logger.getLogger(DealsImportController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    log.info("Not a csv file");
    return "File Is Not A CSV format!";
  }
}
