package com.example.rahyab.service;

import com.example.rahyab.model.Request;
import com.example.rahyab.model.Result;

public interface CalculationService {

    /**
     * Calculates all sub-arrays within the given set which sum of their items are equal to the requested user value
     * @param request the user provided request object contains the array and the target number
     * @return {@link Result} object which contains all the sub-arrays realted to the solution
     * @throws com.example.rahyab.exception.SolutionNotFoundException when no solution is found
     */
    Result calculate(Request request);


}
