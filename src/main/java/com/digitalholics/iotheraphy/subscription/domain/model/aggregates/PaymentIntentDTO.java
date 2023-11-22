package com.digitalholics.iotheraphy.subscription.domain.model.aggregates;

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
