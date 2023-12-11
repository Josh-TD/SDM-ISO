package com.example.sdmisoback.IntegrationTests;

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
public class FiltersIntegrationTest {
    
    @Autowired
    private MockMvc mvc;

    private static String baseUrl = "/api/v3/files/list";
    private static String defaultReqParams = "?pageNum=0&pageSize=10&sortBy=createDate&sortAsc=false";
    private static String defaultRequest = baseUrl + defaultReqParams;

    @Test
    public void Filters_NoFilters_ReturnsOkAndCorrectFirstValue() throws Exception {
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

    @Test 
    public void Filters_Error403_BadFileTypeSingular() throws Exception {
        mvc.perform(get(baseUrl + defaultReqParams + "&fileTypes=jpeg"))
            .andExpect(status().isBadRequest());
    }

    @Test 
    public void Filters_Error403_BadFileTypeMultiple() throws Exception {
        mvc.perform(get(baseUrl + defaultReqParams + "&fileTypes=pdf&fileTypes=jpg&fileTypes=jpeg"))
            .andExpect(status().isBadRequest());
    }

    @Test 
    public void Filters_Error403_BadSortBy() throws Exception {
        mvc.perform(get(baseUrl + "?pageNum=0&pageSize=10&sortBy=description&sortAsc=false"))
            .andExpect(status().isBadRequest());
    }

    @Test 
    public void Filters_Error404_NoMatch() throws Exception {
        mvc.perform(get(baseUrl + defaultReqParams + "&fileId=999999"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void Filters_SingularFilter_FileId() throws Exception {
        mvc.perform(get(defaultRequest + "&fileId=1001"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.empty").value(false))
            .andExpect(jsonPath("$.totalElements").value(1))
            .andExpect(jsonPath("$.totalPages").value(1))
            .andExpect(jsonPath("$.content[0].attachmentId").value(1001));
    }

    @Test
    public void Filters_SingularFilter_FileIdDuplicates() throws Exception {
        mvc.perform(get(defaultRequest + "&fileId=1052"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.empty").value(false))
            .andExpect(jsonPath("$.totalElements").value(8))
            .andExpect(jsonPath("$.content[0].attachmentId").value(1052));
    }

    @Test
    public void Filters_SingularFilter_FileDescription() throws Exception {
        mvc.perform(get(defaultRequest + "&fileDescription=resource"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.empty").value(false))
            .andExpect(jsonPath("$.totalElements").value(18))
            .andExpect(jsonPath("$.content[0].attachmentId").value(1013));
    }

    @Test
    public void Filters_SingularFilter_FileCreateDate() throws Exception {
        mvc.perform(get(defaultRequest + "&createdSince=2015-03-08T00:00:00"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.empty").value(false))
            .andExpect(jsonPath("$.totalElements").value(40))
            .andExpect(jsonPath("$.content[0].attachmentId").value(1042));
    }

    
}
