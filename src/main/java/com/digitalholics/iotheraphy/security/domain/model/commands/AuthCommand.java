package com.digitalholics.iotheraphy.security.domain.model.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthCommand {

    @JsonProperty("access_Token")
    private String accessToken;

    @JsonProperty("refresh_Token")
    private String refreshToken;
}
