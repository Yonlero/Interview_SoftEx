package com.softexpert.interview.core.dtos;

import com.softexpert.interview.core.interfaces.IDTO;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataPersonValueDTO implements IDTO {
    private String personName;
    private Double value;
    private String paymentLink;
}