package com.example.sdmisoback.UnitTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.sdmisoback.BackendServerApp;
import com.example.sdmisoback.repository.FiltersRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = BackendServerApp.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
public class FiltersControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @Autowired
    private FiltersRepo filtersRepo;

    private static String baseUrl = "/api/v3/files/list";
    private static String defaultReqParams = "?pageNum=0&pageSize=10&sortBy=createDate&sortAsc=false";

    @Test
    public void FiltersController_NoFilters_Test() throws Exception {
        mvc.perform(get(baseUrl + defaultReqParams))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.content[0].attachmentId").value(1042))
            .andExpect(jsonPath("$.totalElements").value(142))
            .andExpect(jsonPath("$.totalPages").value(15))
            .andExpect(jsonPath("$.size").value(10))
            .andExpect(jsonPath("$.number").value(0))
            .andExpect(jsonPath("$.first").value(true))
            .andExpect(jsonPath("$.last").value(false))
            .andExpect(jsonPath("$.empty").value(false));
    }
}
