package com.digitalholics.iotheraphy.Payment.domain.model;

import lombok.*;

@Getter
@Setter

public class PaymentIntentDTO {
    public enum Currency {
        PEN
    }
    private int amount;
    private Currency currency;
}
