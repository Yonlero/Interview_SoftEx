package com.softexpert.interview.service;

import com.softexpert.interview.core.dtos.AmountDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProcessAmountService {
    public AmountDataDTO processAmountValues(AmountDataDTO amountDTO) {
        Map<String, Double> percentByPeople = new HashMap<>();
        for (Map.Entry<String, List<Double>> input : amountDTO.getMapPeople().entrySet()) {
//            percentByPeople.put(input.getKey(), input.getValue().stream().map(x -> {
//
//            }));
        }
        return AmountDataDTO.builder().totalWithoutDiscount(10.0).build();
    }
}