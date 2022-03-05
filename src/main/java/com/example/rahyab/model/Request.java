package com.example.rahyab.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class Request {
    private final Integer       sum;
    private final List<Integer> set;
}
