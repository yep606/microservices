package org.example.apigateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import ru.rogoff.jwt.JwtTokenUtil;

@Slf4j
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthenticationFilter(JwtTokenUtil jwtTokenUtil) {
        super(Config.class);
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info("Intercepting request for authentication");

            String authorizationHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            validateHeader(authorizationHeader);

            String token = extractToken(authorizationHeader);
            validateToken(token);

            return chain.filter(exchange);
        };
    }

    /**
     * Handle the case where the Authorization header is missing.
     */
    private void validateHeader(String authorizationHeader) {
        if (StringUtils.isEmpty(authorizationHeader)) {
            log.error("Missing Authorization header");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authorization header missing");
        }
    }


    /**
     * Extracts the JWT token from the Authorization header.
     *
     * @param authorizationHeader the Authorization header value
     * @return the extracted JWT token
     */
    private String extractToken(String authorizationHeader) {
        if (!authorizationHeader.startsWith(BEARER_PREFIX)) {
            log.error("Invalid Authorization header format");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Authorization token must start with 'Bearer '");
        }
        return authorizationHeader.substring(BEARER_PREFIX.length());
    }

    /**
     * Validate the JWT token using the utility.
     *
     * @param token the JWT token
     */
    private void validateToken(String token) {
        try {
            jwtTokenUtil.validate(token);
            log.info("JWT token validation successful");
        } catch (Exception e) {
            log.error("JWT token validation failed: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid JWT token");
        }
    }

    public static class Config {
        // Add any configuration properties for the filter if needed in the future
    }
}
