package com.digitalholics.iotheraphy.security.domain.model.commands;

import com.digitalholics.iotheraphy.security.domain.model.valueobjects.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCommand {
    String username;
    String password;
    String firstname;
    String lastname;
    private Role role;
}
