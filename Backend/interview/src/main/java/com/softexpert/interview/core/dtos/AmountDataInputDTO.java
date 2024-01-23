package com.softexpert.interview.core.dtos;

import com.softexpert.interview.core.interfaces.IDTO;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import static java.util.Objects.isNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AmountDataInputDTO implements IDTO {
    private Double totalWithoutDiscountOrAdditions;
    private List<Double> discount;
    private List<Double> additions;
    private Double freight;
    private HashMap<String, List<Double>> mapPeople;

    public void calculateTotal() {
        if (isNull(this.getTotalWithoutDiscountOrAdditions())) {
            this.setTotalWithoutDiscountOrAdditions(0.0);
            for (Entry<String, List<Double>> input : mapPeople.entrySet()) {
                input.getValue().forEach(
                        i -> this.setTotalWithoutDiscountOrAdditions(this.getTotalWithoutDiscountOrAdditions() + i)
                );
            }
        }
    }
}