package com.softexpert.interview.validator;

import com.softexpert.interview.core.dtos.AmountDataInputDTO;
import com.softexpert.interview.core.exceptions.IntegratedException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.isNull;

@Component
public class AmountDataValidator {

    public void checkFieldsAmount(AmountDataInputDTO amountDTO) {
        checkTotalValueInput(amountDTO);
        checkNegativeValuesInPercent(amountDTO);
        for (Map.Entry<String, List<Double>> input : amountDTO.getMapPeople().entrySet()) {
            input.getValue().forEach(value -> {
                checkNullValues(value);
                checkNegativeValues(value);
                checkNaNValues(value);
            });
        }
    }

    private void checkNaNValues(Double value) {
        if (value.isNaN()) {
            throw new IntegratedException("O Valor não é um número");
        }
    }

    private void checkNullValues(Double value) {
        if (isNull(value)) {
            throw new IntegratedException("O valor é nulo");
        }
    }

    private void checkNegativeValues(Double value) {
        if (value < 0) {
            throw new IntegratedException("O Valor não é menor que zero");
        }
    }

    private void checkNegativeValuesInPercent(AmountDataInputDTO amountDTO) {
        amountDTO.getAdditionsInPercent().forEach(value -> {
            if (value == null)
                return;
            if (value.isNaN() || value < 0) {
                throw new IntegratedException("O valor no campo de adicionais em porcentagens não é valido");
            }
        });

        amountDTO.getDiscountInPercent().forEach(value -> {
            if (value == null)
                return;
            if (value.isNaN() || value < 0) {
                throw new IntegratedException("O valor no campo de descontos em porcentagens não é valido");
            }
        });
    }

    private void checkTotalValueInput(AmountDataInputDTO amountDTO) {
        amountDTO.calculateTotal();
        amountDTO.setFreight(amountDTO.getFreight() != null ? amountDTO.getFreight() : 0.0);
        amountDTO.setAdditionsInReal(checkListNullElements(amountDTO.getAdditionsInReal()));
        amountDTO.setDiscountInReal(checkListNullElements(amountDTO.getDiscountInReal()));
        amountDTO.setAdditionsInPercent(checkListNullElements(amountDTO.getAdditionsInPercent()));
        amountDTO.setDiscountInPercent(checkListNullElements(amountDTO.getDiscountInReal()));
    }

    private List<Double> checkListNullElements(List<Double> list) {
        if (isNull(list))
            return List.of();
        if (list.stream().allMatch(Objects::isNull))
            return List.of();
        return list;
    }
}