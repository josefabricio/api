package com.digitalholics.iotheraphy.subscription.domain.services;

import com.digitalholics.iotheraphy.subscription.domain.model.aggregates.PaymentIntentDTO;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface PaymentQueryService {
    PaymentIntent paymentIntent(PaymentIntentDTO paymentIntentDTO) throws StripeException;
    PaymentIntent confirm(String id) throws StripeException;

    PaymentIntent cancel(String id) throws StripeException;
}
