package com.severo.ecommerce.payment.service;

import com.severo.ecommerce.checkout.event.CheckoutCreatedEvent;
import com.severo.ecommerce.payment.entity.PaymentEntity;
import com.severo.ecommerce.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;

    @Override
    public Optional<PaymentEntity> create(CheckoutCreatedEvent checkoutCreatedEvent) {
        final PaymentEntity paymentEntity = PaymentEntity.builder()
                .checkoutCode(checkoutCreatedEvent.getCheckoutCode().toString())
                .code(UUID.randomUUID().toString())
                .build();

        paymentRepository.save(paymentEntity);
        return Optional.of(paymentEntity);
    }
}
