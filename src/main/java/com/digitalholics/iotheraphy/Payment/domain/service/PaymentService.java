package com.digitalholics.iotheraphy.Payment.domain.service;

import com.digitalholics.iotheraphy.Payment.domain.model.PaymentIntentDTO;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface PaymentService {
    PaymentIntent paymentIntent(PaymentIntentDTO paymentIntentDTO) throws StripeException;
    PaymentIntent confirm(String id) throws StripeException;

    PaymentIntent cancel(String id) throws StripeException;
}
