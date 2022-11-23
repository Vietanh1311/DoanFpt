package com.company.demo.service;

import com.company.demo.entity.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> listPayment();

    Payment getOne(Long id);
}
