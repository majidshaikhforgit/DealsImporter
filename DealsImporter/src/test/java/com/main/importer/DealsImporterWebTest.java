/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.importer;

import com.main.controller.DealsImportController;
import com.main.service.DealsImporterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 *
 * @author webwerks
 */
@RunWith(SpringRunner.class)
public class DealsImporterWebTest {

  private MockMvc mvc;
  @MockBean
  private DealsImporterService dealImporterService;

  @Before
  public void setUp() {
    mvc = MockMvcBuilders.standaloneSetup(new DealsImportController()).build();
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
//    mvc.perform(post("/enquirefile")).param("","")
//            .andExpect(status().isOk())
//            .andDo(print());
  }
  
 
}
