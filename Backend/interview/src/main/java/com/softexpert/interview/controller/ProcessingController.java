package com.softexpert.interview.controller;

import com.softexpert.interview.core.dtos.AmountDataInputDTO;
import com.softexpert.interview.core.dtos.AmountDataOutputDTO;
import com.softexpert.interview.core.enums.PaymentMethodsEnum;
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
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<AmountDataOutputDTO> processAmount(@RequestBody AmountDataInputDTO amountDTO,
                                                             @RequestParam String paymentMethod) {
        AmountDataOutputDTO outputDTO = service.processAmountValues(amountDTO,
                PaymentMethodsEnum.valueOf(paymentMethod));
        return ResponseEntity.ok(outputDTO);
    }
}