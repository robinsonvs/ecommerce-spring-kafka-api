package com.severo.ecommerce.payment.listener;

import com.severo.ecommerce.checkout.event.CheckoutCreatedEvent;
import com.severo.ecommerce.payment.streaming.CheckoutProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckoutCreatedListener {

    private final CheckoutProcessor checkoutProcessor;

    @StreamListener(CheckoutProcessor.INPUT)
    public void handler(CheckoutCreatedEvent event) {
        //payment process gateway
        //payment save database
        //send event to payment process

    }
}
