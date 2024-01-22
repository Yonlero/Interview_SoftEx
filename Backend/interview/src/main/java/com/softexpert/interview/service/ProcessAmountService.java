package com.softexpert.interview.service;

import com.softexpert.interview.core.dtos.AmountDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessAmountService {
    public AmountDataDTO processAmountValues(AmountDataDTO amountDTO) {
        return AmountDataDTO.builder().total(10.0).build();
    }
}