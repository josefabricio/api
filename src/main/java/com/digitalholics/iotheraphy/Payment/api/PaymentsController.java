package com.digitalholics.iotheraphy.Payment.api;

import com.digitalholics.iotheraphy.Payment.domain.model.PaymentIntentDTO;
import com.digitalholics.iotheraphy.Payment.domain.service.PaymentService;
import com.digitalholics.iotheraphy.Payment.service.PaymentServiceImpl;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/payments", produces = "application/json")
//@Tag(name = "Payments", description = "Create, read, update and delete payments")
public class PaymentsController {

    final
    PaymentServiceImpl paymentService;

    public PaymentsController(PaymentServiceImpl paymentService) {
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
