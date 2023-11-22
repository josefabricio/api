package com.digitalholics.iotheraphy.subscription.interfaces;

import com.digitalholics.iotheraphy.subscription.domain.model.aggregates.PaymentIntentDTO;
import com.digitalholics.iotheraphy.subscription.application.internal.queryservices.PaymentQueryServiceImpl;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/payments", produces = "application/json")
//@Tag(name = "Payments", description = "Create, read, update and delete payments")
public class PaymentsController {

    final
    PaymentQueryServiceImpl paymentService;

    public PaymentsController(PaymentQueryServiceImpl paymentService) {
        this.paymentService = paymentService;
    }


    @PostMapping("/paymentIntent")
    public ResponseEntity<String> payment(@RequestBody PaymentIntentDTO paymentIntentDTO) throws StripeException {
        PaymentIntent paymentIntent = paymentService.paymentIntent(paymentIntentDTO);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }
    @PostMapping("/confirm/{id}")
    public ResponseEntity<String> confirm(@PathVariable("id") String id) throws StripeException {
        PaymentIntent paymentIntent = paymentService.confirm(id);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<String> cancel(@PathVariable("id") String id) throws StripeException {
        PaymentIntent paymentIntent = paymentService.cancel(id);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }

}
