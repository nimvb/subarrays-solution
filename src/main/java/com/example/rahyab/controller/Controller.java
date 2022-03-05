package com.example.rahyab.controller;

import com.example.rahyab.model.Request;
import com.example.rahyab.model.Result;
import com.example.rahyab.service.CalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/calculations")
@RequiredArgsConstructor
@Validated
public class Controller {

    private final CalculationService calculationService;

    @PostMapping
    public Result calculate(@RequestBody Request request){
        return calculationService.calculate(request);
    }
}
