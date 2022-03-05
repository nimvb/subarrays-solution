package com.example.rahyab.service;

import com.example.rahyab.exception.SolutionNotFoundException;
import com.example.rahyab.model.Request;
import com.example.rahyab.model.Result;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalculationServiceImplTest {

    @Autowired
    CalculationServiceImpl calculationService;

    @Test
    void ShouldThrownAnExceptionWhenNoSolutionExists(){
        Request request = new Request(6, List.of(1,1));
        Assertions.assertThatThrownBy(() -> {
            calculationService.calculate(request);
        }).isInstanceOf(SolutionNotFoundException.class);
    }

    @Test
    void ShouldReturnProperSolutionWheneverAValidParametersAreProvided(){
        Request request = new Request(3, List.of(1,1,1));
        Result  result = calculationService.calculate(request);
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getSubArrays()).hasSize(1);
        Assertions.assertThat(result.getSubArrays()).contains(List.of(1,1,1));
    }


}