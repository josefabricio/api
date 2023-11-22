package com.digitalholics.iotheraphy.security.domain.services;

import com.digitalholics.iotheraphy.security.domain.model.queries.GetAllUsersQuery;
import com.digitalholics.iotheraphy.security.domain.model.queries.GetUserByIdQuery;
import  com.digitalholics.iotheraphy.security.domain.model.aggregates.User;
import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);
    Optional<User> handle(GetUserByIdQuery query);
}
