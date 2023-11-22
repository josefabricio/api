package com.digitalholics.iotheraphy.security.domain.services;

import com.digitalholics.iotheraphy.security.domain.model.entities.Token;
import com.digitalholics.iotheraphy.security.domain.model.queries.GetAllTokensQuery;
import com.digitalholics.iotheraphy.security.domain.model.queries.GetTokenByNameQuery;

import java.util.List;
import java.util.Optional;

public interface TokenQueryService {
        List<Token> handle(GetAllTokensQuery query);
        Optional<Token> handle(GetTokenByNameQuery query);
}
