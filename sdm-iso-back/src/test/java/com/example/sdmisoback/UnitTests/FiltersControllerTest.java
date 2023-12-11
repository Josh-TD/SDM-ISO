package com.example.sdmisoback.UnitTests;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.sdmisoback.BackendServerApp;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = BackendServerApp.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
public class FiltersControllerTest {
    
    @Autowired
    private MockMvc mvc;

    
}
