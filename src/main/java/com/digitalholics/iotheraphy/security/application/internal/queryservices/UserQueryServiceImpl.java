package com.digitalholics.iotheraphy.security.application.internal.queryservices;

import com.digitalholics.iotheraphy.security.domain.model.aggregates.User;
import com.digitalholics.iotheraphy.security.domain.model.queries.GetAllUsersQuery;
import com.digitalholics.iotheraphy.security.domain.model.queries.GetUserByIdQuery;
import com.digitalholics.iotheraphy.security.domain.services.UserQueryService;
import com.digitalholics.iotheraphy.security.infrastructure.persistance.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl  implements UserQueryService{
    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }
}
