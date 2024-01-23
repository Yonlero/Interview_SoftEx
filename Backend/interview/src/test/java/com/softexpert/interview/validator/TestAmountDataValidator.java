package com.softexpert.interview.validator;

import com.softexpert.interview.core.dtos.AmountDataInputDTO;
import com.softexpert.interview.core.exceptions.IntegratedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class TestAmountDataValidator {
    AmountDataInputDTO amount = null;

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

    @InjectMocks
    AmountDataValidator validator;

    @Test
    void checkFieldsAmount() {
        assertDoesNotThrow(() -> validator.checkFieldsAmount(amount));
    }

    @Test
    void checkFieldsAmountThrowsException() {
        amount.setMapPeople(new HashMap<String, List<Double>>() {{
            put("Test_1", List.of(40.0, -2.0));
            put("Test_2", List.of(-8.0));
        }});
        assertThrows(IntegratedException.class, () -> validator.checkFieldsAmount(amount));
    }

    @Test
    void checkFieldsAmountThrowsNegativeDiscountPercentException() {
        amount.setDiscountInPercent(List.of(-1.0));
        assertThrows(IntegratedException.class, () -> validator.checkFieldsAmount(amount));
    }

    @Test
    void checkFieldsAmountThrowsNegativeAdditionPercentException() {
        amount.setAdditionsInPercent(List.of(-1.0));
        assertThrows(IntegratedException.class, () -> validator.checkFieldsAmount(amount));
    }
}