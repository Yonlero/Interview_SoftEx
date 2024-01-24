package com.softexpert.interview.service.payment;

import com.softexpert.interview.core.interfaces.IPayment;
import org.springframework.stereotype.Service;

@Service
public record PicpayPaymentService() implements IPayment {

    @Override
    public String generatePaymentLink(Double amount, String receiver) {
        return "https://picpay.me/" + receiver + "/" + amount.toString();
    }
}