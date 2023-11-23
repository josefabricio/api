package com.digitalholics.iotheraphy.profile.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record CellPhone(String pre, String cellNumber) {
    public CellPhone(){
        this("+51", "955110309");
    }
}
