package com.severo.ecommerce.payment.listener;

import com.severo.ecommerce.checkout.event.CheckoutCreatedEvent;
import com.severo.ecommerce.payment.entity.PaymentEntity;
import com.severo.ecommerce.payment.event.PaymentCreatedEvent;
import com.severo.ecommerce.payment.service.PaymentService;
import com.severo.ecommerce.payment.streaming.CheckoutProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckoutCreatedListener {

    private final CheckoutProcessor checkoutProcessor;

    private final PaymentService paymentService;

    @StreamListener(CheckoutProcessor.INPUT)
    public void handler(CheckoutCreatedEvent event) {
        log.info("CheckoutCreatedEvent={}", event);
        //payment process gateway
        //payment save database
        //send event to payment process
        final PaymentEntity paymentEntity = paymentService.create(event).orElseThrow(RuntimeException::new);
        final PaymentCreatedEvent paymentCreatedEvent = PaymentCreatedEvent.newBuilder()
                .setCheckoutCode(paymentEntity.getCheckoutCode())
                .setPaymentCode(paymentEntity.getCode())
                .build();
        checkoutProcessor.output().send(MessageBuilder.withPayload(paymentCreatedEvent).build());

    }
}
