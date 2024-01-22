package com.softexpert.interview.core.dtos;

import com.softexpert.interview.core.interfaces.IDTO;
import lombok.*;

import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AmountDataDTO implements IDTO {
    private Double total;
    private List<Double> discount;
    private List<Double> additions;
    private Map<String, List<Double>> mapPeople;
}