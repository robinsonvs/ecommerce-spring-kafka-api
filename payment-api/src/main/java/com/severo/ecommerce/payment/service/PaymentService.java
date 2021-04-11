package com.severo.ecommerce.payment.service;

import com.severo.ecommerce.checkout.event.CheckoutCreatedEvent;
import com.severo.ecommerce.payment.entity.PaymentEntity;

import java.util.Optional;

public interface PaymentService {

    Optional<PaymentEntity> create(CheckoutCreatedEvent checkoutCreatedEvent);
}
