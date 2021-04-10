package com.severo.ecommerce.checkout.service;

import com.severo.ecommerce.checkout.entity.CheckoutEntity;
import com.severo.ecommerce.checkout.resource.checkout.CheckoutRequest;

import java.util.Optional;

public interface CheckoutService {

    Optional<CheckoutEntity> create(CheckoutRequest checkoutRequest);
}
