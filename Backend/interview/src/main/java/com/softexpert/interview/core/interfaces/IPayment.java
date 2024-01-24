package com.softexpert.interview.core.interfaces;

public interface IPayment {
    String generatePaymentLink(Double amount, String receiver);
}