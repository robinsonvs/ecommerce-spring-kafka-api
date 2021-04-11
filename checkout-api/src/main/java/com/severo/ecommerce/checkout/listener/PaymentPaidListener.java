package com.severo.ecommerce.checkout.listener;

import com.severo.ecommerce.checkout.entity.CheckoutEntity;
import com.severo.ecommerce.checkout.repository.CheckoutRepository;
import com.severo.ecommerce.checkout.streaming.PaymentPaidSink;
import com.severo.ecommerce.payment.event.PaymentCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentPaidListener {

    private final CheckoutRepository checkoutRepository;

    @StreamListener(PaymentPaidSink.INPUT)
    public void handle(PaymentCreatedEvent event) {
        final CheckoutEntity checkoutEntity = checkoutRepository.findByCode(event.getCheckoutCode().toString()).orElseThrow(RuntimeException::new);
        checkoutEntity.setStatus(CheckoutEntity.Status.APPROVED);
        checkoutRepository.save(checkoutEntity);
    }
}
