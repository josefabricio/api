package com.digitalholics.iotheraphy.profile.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Ubication(Double latitude, Double altitude) {
    public Ubication(){
        this(0.1,0.1);
    }

}
