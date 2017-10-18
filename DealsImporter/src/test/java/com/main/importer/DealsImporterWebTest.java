/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.importer;

import com.main.controller.DealsImportController;
import com.main.service.DealsImporterService;
import com.main.service.DealsImporterServiceImpl;
import java.io.FileInputStream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 *
 * @author Majid
 */
@RunWith(SpringRunner.class)
public class DealsImporterWebTest {

  private MockMvc mvc;
  @MockBean
  private DealsImporterService importerService;

  @InjectMocks
  private DealsImportController dealsImportController;

  @Before
  public void setUp() {

    MockitoAnnotations.initMocks(this);
    mvc = MockMvcBuilders.standaloneSetup(dealsImportController).build();
    Mockito.when(importerService.enquireByFileName("filename")).thenReturn("Valid count 10 Invalid count 1");
    Mockito.when(importerService.processInputFile("somepath", "somecsvfile")).thenReturn(Boolean.TRUE);
  }

  @Test
  public void whenHome_thenReturnViewPage() throws Exception {

    mvc.perform(get("/importdeals"))
            .andExpect(status().isOk())
            .andExpect(view().name("home"))
            .andDo(print());
  }

  @Test
  public void whenEnquire_thenReturnViewPage() throws Exception {
    mvc.perform(get("/enquirefile"))
            .andExpect(status().isOk())
            .andExpect(view().name("enquire"))
            .andDo(print());
  }

  @Test
  public void whenEnquirePost_thenAcceptFileNameAndReturnValidAndInvalidFileCount() throws Exception {

    mvc.perform(post("/enquire").param("name", "deals.csv"))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  public void whenUploadFile_thenProcessFile() throws Exception {
    String fileName = "deals.csv";
    String workingDir = System.getProperty("user.dir");
    String storePath = workingDir + "/uploads/";
    FileInputStream fis = new FileInputStream(storePath + fileName);
    MockMultipartFile multipartFile = new MockMultipartFile("file", fileName, "multipart/form-data", fis);
    mvc.perform(MockMvcRequestBuilders.fileUpload("/uploadfile").file(multipartFile)).andExpect(status().isOk());
  }

}
