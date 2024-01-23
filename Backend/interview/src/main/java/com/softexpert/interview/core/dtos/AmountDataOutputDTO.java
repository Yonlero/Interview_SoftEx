package com.softexpert.interview.core.dtos;

import com.softexpert.interview.core.interfaces.IDTO;
import lombok.*;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AmountDataOutputDTO implements IDTO {
    private Double totalWithoutDiscountOrAdditions;
    private Double totalWithDiscountAndAdditions;
    private List<Double> discountInReal;
    private List<Double> additionsInReal;
    private List<Double> discountInPercent;
    private List<Double> additionsInPercent;
    private Double freight;
    private HashMap<String, Double> mapAmountByPeople;

    public static AmountDataOutputDTO buildByAmountInput(AmountDataInputDTO amountDTO) {
        return AmountDataOutputDTO.builder()
                .additionsInReal(amountDTO.getAdditionsInReal())
                .discountInReal(amountDTO.getDiscountInReal())
                .discountInPercent(amountDTO.getDiscountInPercent())
                .additionsInPercent(amountDTO.getAdditionsInPercent())
                .freight(amountDTO.getFreight())
                .totalWithoutDiscountOrAdditions(amountDTO.getTotalWithoutDiscountOrAdditions())
                .build();
    }
}