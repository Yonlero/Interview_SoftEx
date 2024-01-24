package com.softexpert.interview.service;

import com.softexpert.interview.core.dtos.AmountDataInputDTO;
import com.softexpert.interview.core.dtos.AmountDataOutputDTO;
import com.softexpert.interview.core.enums.PaymentMethodsEnum;
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
        AmountDataOutputDTO dto = service.processAmountValues(amount, PaymentMethodsEnum.valueOf("PICPAY"));
        assertThat(dto).isNotNull();
        assertThat(dto.getMapAmountByPeople().get("Test_1")).isEqualTo(List.of(31.92, "https://picpay.me/null/31.92"));
        assertThat(dto.getMapAmountByPeople().get("Test_2")).isEqualTo(List.of(6.08, "https://picpay.me/null/6.08"));
    }

    @Test
    void processAmountValuesAdditionInRealTest() {
        amount.setAdditionsInReal(List.of(10.0));
        AmountDataOutputDTO dto = service.processAmountValues(amount, PaymentMethodsEnum.valueOf("PICPAY"));
        assertThat(dto).isNotNull();
        assertThat(dto.getMapAmountByPeople().get("Test_1")).isEqualTo(List.of(40.32, "https://picpay.me/null/40.32"));
        assertThat(dto.getMapAmountByPeople().get("Test_2")).isEqualTo(List.of(7.68, "https://picpay.me/null/7.68"));
    }

    @Test
    void processAmountValuesAdditionInPercentTest() {
        amount.setAdditionsInPercent(List.of(10.0));
        AmountDataOutputDTO dto = service.processAmountValues(amount, PaymentMethodsEnum.valueOf("PICPAY"));
        assertThat(dto).isNotNull();
        assertThat(dto.getMapAmountByPeople().get("Test_1")).isEqualTo(List.of(36.12, "https://picpay.me/null/36.12"));
        assertThat(dto.getMapAmountByPeople().get("Test_2")).isEqualTo(List.of(6.88, "https://picpay.me/null/6.88"));
    }

    @Test
    void processAmountValuesDiscountInPercentTest() {
        amount.setDiscountInPercent(List.of(10.0));
        AmountDataOutputDTO dto = service.processAmountValues(amount, PaymentMethodsEnum.valueOf("PICPAY"));
        assertThat(dto).isNotNull();
        assertThat(dto.getMapAmountByPeople().get("Test_1")).isEqualTo(List.of(27.72, "https://picpay.me/null/27.72"));
        assertThat(dto.getMapAmountByPeople().get("Test_2")).isEqualTo(List.of(5.28, "https://picpay.me/null/5.28"));
    }
}