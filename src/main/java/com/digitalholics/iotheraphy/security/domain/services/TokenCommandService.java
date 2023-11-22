package com.digitalholics.iotheraphy.security.domain.services;

import com.digitalholics.iotheraphy.security.domain.model.commands.AuthCommand;
import org.springframework.stereotype.Service;

@Service
public interface TokenCommandService {

    void handle(AuthCommand command);

}
