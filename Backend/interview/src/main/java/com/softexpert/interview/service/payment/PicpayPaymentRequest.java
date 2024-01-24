package com.softexpert.interview.service.payment;

import com.softexpert.interview.core.interfaces.IPayment;
import org.springframework.stereotype.Component;

@Component
public class PicpayPaymentRequest implements IPayment {
    public static String createLinkToPayment(Double amount, String username) {
        return "https://picpay.me/" + username + "/" + amount.toString();
    }
}