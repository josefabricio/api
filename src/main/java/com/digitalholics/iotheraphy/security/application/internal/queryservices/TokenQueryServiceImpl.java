package com.digitalholics.iotheraphy.security.application.internal.queryservices;

import com.digitalholics.iotheraphy.security.domain.model.entities.Token;
import com.digitalholics.iotheraphy.security.domain.model.queries.GetAllTokensQuery;
import com.digitalholics.iotheraphy.security.domain.model.queries.GetTokenByNameQuery;
import com.digitalholics.iotheraphy.security.domain.services.TokenQueryService;
import com.digitalholics.iotheraphy.security.infrastructure.persistance.jpa.repositories.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TokenQueryServiceImpl implements TokenQueryService {
    private final TokenRepository tokenRepository;

    public TokenQueryServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public List<Token> handle(GetAllTokensQuery query) {
        return tokenRepository.findAll();
    }

    @Override
    public Optional<Token> handle(GetTokenByNameQuery query) {
        return tokenRepository.findByToken(query.name());
    }
}
