package com.digitalholics.iotheraphy.security.domain.services;

import com.digitalholics.iotheraphy.security.domain.model.commands.LoginCommand;
import com.digitalholics.iotheraphy.security.domain.model.commands.RegisterCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;
import  com.digitalholics.iotheraphy.security.domain.model.aggregates.User;
import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(RegisterCommand command);
    /**
     * This method handles the {@link RegisterCommand} command.
     *
     * @param command the {@link com.digitalholics.iotheraphy.security.domain.model.commands.LoginCommand} command.
     * @return the {@link User} entity.
     */
    Optional<ImmutablePair<User, String>> handle(LoginCommand command);
}
