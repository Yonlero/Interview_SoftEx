package com.softexpert.interview.service;

import com.softexpert.interview.core.dtos.AmountDataInputDTO;
import com.softexpert.interview.core.dtos.AmountDataOutputDTO;
import com.softexpert.interview.validator.AmountDataValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class TestProcessAmountService {
    AmountDataInputDTO amount = null;

    @Mock
    AmountDataValidator validator;
    @InjectMocks
    ProcessAmountService service;

    @BeforeEach
    public void buildInput() {
        amount = AmountDataInputDTO.builder()
                .freight(8.0)
                .totalWithoutDiscountOrAdditions(50.0)
                .additionsInPercent(List.of())
                .discountInPercent(List.of())
                .additionsInReal(List.of())
                .discountInReal(List.of(20.0))
                .mapPeople(new HashMap<String, List<Double>>() {{
                    put("Test_1", List.of(40.0, 2.0));
                    put("Test_2", List.of(8.0));
                }})

                .build();
    }

    @Test
    void processAmountValuesSimpleTest() {
        AmountDataOutputDTO dto = service.processAmountValues(amount);
        assertThat(dto).isNotNull();
        assertThat(dto.getMapAmountByPeople().get("Test_1")).isEqualTo(31.92);
        assertThat(dto.getMapAmountByPeople().get("Test_2")).isEqualTo(6.08);
    }

    @Test
    void processAmountValuesAdditionInRealTest() {
        amount.setAdditionsInReal(List.of(10.0));
        AmountDataOutputDTO dto = service.processAmountValues(amount);
        assertThat(dto).isNotNull();
        assertThat(dto.getMapAmountByPeople().get("Test_1")).isEqualTo(40.32);
        assertThat(dto.getMapAmountByPeople().get("Test_2")).isEqualTo(7.68);
    }

    @Test
    void processAmountValuesAdditionInPercentTest() {
        amount.setAdditionsInPercent(List.of(10.0));
        AmountDataOutputDTO dto = service.processAmountValues(amount);
        assertThat(dto).isNotNull();
        assertThat(dto.getMapAmountByPeople().get("Test_1")).isEqualTo(36.12);
        assertThat(dto.getMapAmountByPeople().get("Test_2")).isEqualTo(6.88);
    }

    @Test
    void processAmountValuesDiscountInPercentTest() {
        amount.setDiscountInPercent(List.of(10.0));
        AmountDataOutputDTO dto = service.processAmountValues(amount);
        assertThat(dto).isNotNull();
        assertThat(dto.getMapAmountByPeople().get("Test_1")).isEqualTo(27.72);
        assertThat(dto.getMapAmountByPeople().get("Test_2")).isEqualTo(5.28);
    }
}