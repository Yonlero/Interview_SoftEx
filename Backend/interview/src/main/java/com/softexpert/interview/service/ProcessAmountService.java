package com.softexpert.interview.service;

import com.softexpert.interview.core.dtos.AmountDataInputDTO;
import com.softexpert.interview.core.dtos.AmountDataOutputDTO;
import com.softexpert.interview.validator.AmountDataValidator;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static com.softexpert.interview.core.dtos.AmountDataOutputDTO.buildByAmountInput;

@Service
public record ProcessAmountService(AmountDataValidator validator) {
    public AmountDataOutputDTO processAmountValues(AmountDataInputDTO amountDTO) {
        validator.checkNegativeValues(amountDTO);
        AmountDataOutputDTO output = buildByAmountInput(amountDTO);
        Map<String, Double> percentByPeople = new HashMap<>();

        for (Map.Entry<String, List<Double>> input : amountDTO.getMapPeople().entrySet()) {
            AtomicReference<Double> percent = new AtomicReference<>(0.0);
            input.getValue().forEach(x -> percent.updateAndGet(v -> v + x));
            percentByPeople.put(input.getKey(), (percent.get() / amountDTO.getTotalWithoutDiscountOrAdditions()) / 100);
        }

        output.setTotalWithDiscountAndAdditions(calculateTotalWithAdditionsAndDiscounts(amountDTO));

        return output;
    }

    private Double calculateTotalWithAdditionsAndDiscounts(AmountDataInputDTO amountDTO) {
        AtomicReference<Double> totalWithAdditionsAndDiscounts = new AtomicReference<>(
                amountDTO.getTotalWithoutDiscountOrAdditions() + amountDTO.getFreight());
        amountDTO.getAdditions().forEach(value ->
                totalWithAdditionsAndDiscounts.set(totalWithAdditionsAndDiscounts.get() + value));
        return totalWithAdditionsAndDiscounts.get();
    }
}