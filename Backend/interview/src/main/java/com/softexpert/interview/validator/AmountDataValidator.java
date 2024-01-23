package com.softexpert.interview.validator;

import com.softexpert.interview.core.dtos.AmountDataDTO;
import com.softexpert.interview.core.exceptions.IntegratedException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class AmountDataValidator {

    public static void checkNegativeValues(AmountDataDTO amountDTO) {
        for (Map.Entry<String, List<Double>> input : amountDTO.getMapPeople().entrySet()) {
            input.getValue().forEach(value -> {
                if (value.isNaN() || value < 0) {
                    throw new IntegratedException("Valor não é um numero ou o valor é menor que 0");
                }
            });
        }
    }
}