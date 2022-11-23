package com.company.demo.service.impl;

import com.company.demo.entity.Payment;
import com.company.demo.repository.PaymentRepository;
import com.company.demo.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public List<Payment> listPayment(){
        return paymentRepository.findAll();
    }

    @Override
    public Payment getOne(Long id){
        return paymentRepository.findById(id).get();
    }




}
