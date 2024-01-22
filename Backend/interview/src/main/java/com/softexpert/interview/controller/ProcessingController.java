package com.softexpert.interview.controller;

import com.softexpert.interview.core.dtos.AmountDataDTO;
import com.softexpert.interview.service.ProcessAmountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProcessingController {
    private final ProcessAmountService service;

    @PostMapping("/process")
    public ResponseEntity<AmountDataDTO> processAmount(@RequestBody(required = false) AmountDataDTO amountDTO) {
        AmountDataDTO outputDTO = service.processAmountValues(amountDTO);
        return ResponseEntity.ok(outputDTO);
    }
}