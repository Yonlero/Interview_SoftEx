package com.softexpert.interview.core.dtos;

import com.softexpert.interview.core.interfaces.IDTO;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;

import static java.util.Objects.isNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AmountDataInputDTO implements IDTO {
    private Double totalWithoutDiscountOrAdditions;
    private Double freight;
    private String receiver;
    private List<Double> discountInReal;
    private List<Double> additionsInReal;
    private List<Double> discountInPercent;
    private List<Double> additionsInPercent;
    private HashMap<String, List<Double>> mapPeople;

    public void validateInputAmunt() {
        if (isNull(this.getTotalWithoutDiscountOrAdditions())) {
            this.setTotalWithoutDiscountOrAdditions(0.0);
            for (Entry<String, List<Double>> input : mapPeople.entrySet()) {
                input.getValue().forEach(
                        i -> this.setTotalWithoutDiscountOrAdditions(this.getTotalWithoutDiscountOrAdditions() + i)
                );
            }
        }
        checkTotalValueInput(this);
        checkListNullElements(this.getDiscountInReal());
        checkListNullElements(this.getAdditionsInReal());
        checkListNullElements(this.getDiscountInPercent());
        checkListNullElements(this.getAdditionsInPercent());
    }

    private void checkTotalValueInput(AmountDataInputDTO amountDTO) {
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