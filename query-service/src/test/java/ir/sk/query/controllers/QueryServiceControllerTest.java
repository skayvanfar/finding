package ir.sk.query.controllers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by sad.kayvanfar on 11/18/2020.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc


//@SpringBootTest
///@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
//@WebMvcTest(ContactServiceController.class)
//@WebMvcTest(ContactServiceController.class) // Spring boot mock
public class QueryServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String contactJson = "{\"name\":\"skayvanfar\",\"phoneNumber\":\"0914\",\"email\":\"hhhh\",\"organization\":\"ghg\",\"github\":\"skayvanfar\"}";


    @Autowired
    private QueryServiceController controller;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}
