package com.softexpert.interview.core.dtos;

import com.softexpert.interview.core.interfaces.IDTO;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AmountDataDTO implements IDTO {
    private Double totalWithoutDiscount;
    private List<Double> discount;
    private List<Double> additions;
    private HashMap<String, List<Double>> mapPeople;

    public void calculateTotal() {
        for (Entry<String, List<Double>> input : mapPeople.entrySet()) {
            input.getValue().forEach(i -> this.setTotalWithoutDiscount(this.getTotalWithoutDiscount() + i));
        }

    }
}