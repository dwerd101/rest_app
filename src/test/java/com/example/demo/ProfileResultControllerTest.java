package com.example.demo;


import com.example.model.ProfileResultView;
import com.example.model.dto.ProfileResultDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProfileResultControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    void updateAllEntities_thenCorrect()  {
        String url = "/task/profileResult/update";
        ProfileResultDto profileResultDto=  new ProfileResultDto();
        profileResultDto.setProfileResultId(10L);
        profileResultDto.setSourceName("Ресурс 1");
        profileResultDto.setOwnersName("Роман");
        profileResultDto.setTablesName("Таблица 2");
        profileResultDto.setFieldName("FirstName");
        profileResultDto.setNameDomain("FIO");
        profileResultDto.setComment("Тест 5");
        profileResultDto.setProfileTaskId(2L);
        List<ProfileResultDto> profileResultDtoList = new ArrayList<>();
        profileResultDtoList.add(profileResultDto);
        String inputJson = mapToJson(profileResultDtoList);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);
    }


    @Test
    @SneakyThrows
    void getProfileResult_thenCorrect() {
        String url = "/task/1/profileResult";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);

    }
    @Test
    @SneakyThrows
    void getProfileResult_thenNoCorrect() {
        String url = "/task/1/profileResult?size=0";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertNotEquals(200,status);
    }


    @SneakyThrows
    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }


}
