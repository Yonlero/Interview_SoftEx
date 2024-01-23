package com.softexpert.interview.controller;

import com.softexpert.interview.core.dtos.AmountDataInputDTO;
import com.softexpert.interview.core.dtos.AmountDataOutputDTO;
import com.softexpert.interview.service.ProcessAmountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProcessingController {
    private final ProcessAmountService service;

    @PostMapping("/process")
    public ResponseEntity<AmountDataOutputDTO> processAmount(@RequestBody AmountDataInputDTO amountDTO) {
        AmountDataOutputDTO outputDTO = service.processAmountValues(amountDTO);
        return ResponseEntity.ok(outputDTO);
    }
}