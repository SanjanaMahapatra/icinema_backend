package com.infy.gateway.filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GlobalFilter {

	@Value("${jwt.secret}")
	private String secretKey;

	private static final List<String> EXCLUDED_PATHS = List.of("/user/login", "/user/register",
			"/moviesms");

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		if (isExcludedPath(exchange)) {
			return chain.filter(exchange);
		}

		String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		if (token == null || !token.startsWith("Bearer ")) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}
		token = token.substring(7);
		try {
			Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
			exchange.getRequest().mutate().header("user-name", claims.getSubject());
		} catch (Exception e) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}
		return chain.filter(exchange);
	}

	private boolean isExcludedPath(ServerWebExchange exchange) {
		String path = exchange.getRequest().getURI().getPath();
        return EXCLUDED_PATHS.stream().anyMatch(path::startsWith);
	}
}