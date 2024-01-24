package com.softexpert.interview.service;

import com.softexpert.interview.core.dtos.AmountDataInputDTO;
import com.softexpert.interview.core.dtos.AmountDataOutputDTO;
import com.softexpert.interview.core.enums.PaymentMethodsEnum;
import com.softexpert.interview.validator.AmountDataValidator;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;

import static com.softexpert.interview.core.dtos.AmountDataOutputDTO.buildByAmountInput;


@Service
public record ProcessAmountService(AmountDataValidator validator) {
    public AmountDataOutputDTO processAmountValues(AmountDataInputDTO amountDTO,
                                                   PaymentMethodsEnum paymentMethodsEnum) {
        validator.checkFieldsAmount(amountDTO);
        AmountDataOutputDTO output = buildByAmountInput(amountDTO);
        output.setTotalWithDiscountAndAdditions(calculateTotalWithAdditionsAndDiscounts(amountDTO));

        HashMap<String, Double> percentByPeople = new HashMap<>();
        HashMap<String, List<Object>> totalByPeople = new HashMap<>();

        for (Entry<String, List<Double>> input : amountDTO.getMapPeople().entrySet()) {
            AtomicReference<Double> percent = new AtomicReference<>(0.0);
            input.getValue().forEach(x -> percent.updateAndGet(v -> v + x));
            percentByPeople.put(input.getKey(), (percent.get() * 100) / amountDTO.getTotalWithoutDiscountOrAdditions());
        }

        for (Entry<String, Double> people : percentByPeople.entrySet()) {
            Double amount = (people.getValue() * output.getTotalWithDiscountAndAdditions()) / 100;
            totalByPeople.put(
                    people.getKey(),
                    List.of(amount, paymentMethodsEnum.paymentMethodChoose(paymentMethodsEnum).generatePaymentLink(
                            amount, amountDTO.getReceiver())
                    )
            );
        }

        output.setMapAmountByPeople(totalByPeople);

        return output;
    }

    private Double calculateTotalWithAdditionsAndDiscounts(AmountDataInputDTO amountDTO) {
        AtomicReference<Double> totalWithAdditionsAndDiscounts = new AtomicReference<>(
                amountDTO.getTotalWithoutDiscountOrAdditions() + amountDTO.getFreight());
        amountDTO.getAdditionsInReal().forEach(value ->
                totalWithAdditionsAndDiscounts.set(totalWithAdditionsAndDiscounts.get() + value));
        amountDTO.getDiscountInReal().forEach(discount -> {
            totalWithAdditionsAndDiscounts.set(totalWithAdditionsAndDiscounts.get() - discount);
        });
        amountDTO.getAdditionsInPercent().forEach(additionPercent -> {
            totalWithAdditionsAndDiscounts.set(totalWithAdditionsAndDiscounts.get() +
                    ((additionPercent / 100) * amountDTO.getTotalWithoutDiscountOrAdditions()));
        });
        amountDTO.getDiscountInPercent().forEach(discountPercent -> {
            totalWithAdditionsAndDiscounts.set(
                    totalWithAdditionsAndDiscounts.get() -
                            ((discountPercent / 100) * amountDTO.getTotalWithoutDiscountOrAdditions()));
        });
        return totalWithAdditionsAndDiscounts.get();
    }
}