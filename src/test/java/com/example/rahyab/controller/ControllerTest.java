package com.example.rahyab.controller;

import com.example.rahyab.exception.SolutionNotFoundException;
import com.example.rahyab.model.Request;
import com.example.rahyab.model.Result;
import com.example.rahyab.service.CalculationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Sets;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(Controller.class)
class ControllerTest {

    @MockBean
    CalculationService calculationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @BeforeEach
    void init(){
        Mockito.when(calculationService.calculate(ArgumentMatchers.any(Request.class))).thenAnswer(invocationOnMock -> {
            Request argument = invocationOnMock.getArgument(0);
            if(argument.getSum() == 3){
                if(argument.getSet().equals(List.of(1,1,1))) {
                    return new Result(List.of(List.of(1, 1, 1)));
                }
            }
            throw new SolutionNotFoundException("solution not found!");

        });
    }

    @Test
    void ShouldReturnResponseWithNotFoundStatusCodeWhenNoSolutionExists() throws Exception {
        Request request = new Request(10,List.of(1,1,1));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/calculations")
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", AllOf.allOf(StringContains.containsString("solution not found!"))))
                .andReturn();
        Assertions.assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }


    @Test
    void ShouldReturnResponseWithOkStatusCodeWhenSolutionExists() throws Exception {
        Request request = new Request(3,List.of(1,1,1));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/calculations")
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.subArrays").isArray())
                .andReturn();
        Assertions.assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }


    @TestConfiguration
    static class Configuration {
        @Bean
        ObjectMapper mapper(){
            return new ObjectMapper();
        }
    }

}