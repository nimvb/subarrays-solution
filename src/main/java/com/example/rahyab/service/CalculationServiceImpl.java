package com.example.rahyab.service;

import com.example.rahyab.exception.SolutionNotFoundException;
import com.example.rahyab.model.Request;
import com.example.rahyab.model.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService{
    @Override
    public Result calculate(Request request) {
        Integer       sum = request.getSum();
        List<Integer> numbers = request.getSet();
        List<List<Integer>> subArrays = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                List<Integer> seq = new ArrayList<>();
                int current = 0;
                for (int start = i; start <= j; start++) {
                    current += numbers.get(start);
                    seq.add(numbers.get(start));
                }
                if (current == sum){
                    subArrays.add(seq);
                }

            }
        }
        if(subArrays.isEmpty()){
            throw new SolutionNotFoundException("solution not found!");
        }
        return new Result(subArrays);
    }
}
