package com.example.rahyab.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class Result {
    private final Collection<List<Integer>> subArrays;
}
