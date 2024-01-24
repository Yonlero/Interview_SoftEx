package com.softexpert.interview.core.enums;

import com.softexpert.interview.core.exceptions.BusinessRuleException;
import com.softexpert.interview.core.interfaces.IPayment;
import com.softexpert.interview.service.payment.PicpayPaymentService;

import static java.util.Objects.requireNonNull;


public enum PaymentMethodsEnum {
    PICPAY;

    public IPayment paymentMethodChoose(PaymentMethodsEnum method) {
        if (requireNonNull(method) == PaymentMethodsEnum.PICPAY) {
            return new PicpayPaymentService();
        }
        throw new BusinessRuleException("Método de pagamento não disponível.");
    }
}