package com.example.sdmisoback.UnitTests;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.sdmisoback.BackendServerApp;



@RunWith(SpringRunner.class)
@SpringBootTest(    
    webEnvironment = WebEnvironment.MOCK,
    classes = BackendServerApp.class)
@TestPropertySource(locations = "classpath:test.properties")
public class FiltersUnitTest {

    @Before
    public void setUp() {
        // Maybe do something
    }
    
}
