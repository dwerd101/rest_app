package com.example.demo;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
public class ProfileTaskControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Test
    @SneakyThrows
    void getAllProfileTask_thenCorrect() {
        final String URl  = "/task/1/profileTask";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URl)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

    }
}
