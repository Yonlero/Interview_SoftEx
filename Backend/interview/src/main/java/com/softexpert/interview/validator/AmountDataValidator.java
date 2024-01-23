package com.softexpert.interview.validator;

import com.softexpert.interview.core.dtos.AmountDataInputDTO;
import com.softexpert.interview.core.exceptions.IntegratedException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AmountDataValidator {

    public void checkNegativeValues(AmountDataInputDTO amountDTO) {
        checkTotalValueInput(amountDTO);
        for (Map.Entry<String, List<Double>> input : amountDTO.getMapPeople().entrySet()) {
            input.getValue().forEach(value -> {
                if (value.isNaN() || value < 0) {
                    throw new IntegratedException("Valor não é um numero ou o valor é menor que 0");
                }
            });
        }
    }

    private void checkTotalValueInput(AmountDataInputDTO amountDTO) {
        amountDTO.calculateTotal();
    }
}