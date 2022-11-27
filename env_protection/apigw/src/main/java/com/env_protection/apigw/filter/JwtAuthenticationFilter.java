//package com.env_protection.apigw.filter;
//
//import com.env_protection.jwt.JwtUtil;
//import io.jsonwebtoken.JwtException;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//import java.util.function.Predicate;
//
//@Component
//@AllArgsConstructor
//@Slf4j
//public class JwtAuthenticationFilter implements GatewayFilter {
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        exchange.get
//        ServerHttpRequest request = exchange.getRequest();
//        final List<String> apiEndpoints =
//                List.of("/signup", "/login");
//        Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints
//                .stream()
//                .noneMatch(uri ->
//                        r
//                        .getURI()
//                        .getPath()
//                        .contains(uri));
////
////        if (isApiSecured.test(request)) {
////            if (!request.getHeaders().containsKey("Authorization")) {
////                ServerHttpResponse response = exchange.getResponse();
////                response.setStatusCode(HttpStatus.UNAUTHORIZED);
////                return response.setComplete();
////            }
////
////            final String token = request
////                    .getHeaders()
////                    .getOrEmpty("Authorization")
////                    .get(0);
////            try {
////                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
////                        = JwtUtil.getUsernamePasswordAuthenticationToken(token);
////                //Claims claims = jwtUtil.getClaims(token);
////                request.mutate()
////                        .header("username", String.valueOf(usernamePasswordAuthenticationToken.getPrincipal()))
////                        .build();
////            } catch (JwtException e) {
////                // e.printStackTrace();
////                log.info("Invalid jwt: {}", e.getMessage());
////                ServerHttpResponse response = exchange.getResponse();
////                response.setStatusCode(HttpStatus.BAD_REQUEST);
////                return response.setComplete();
////            }
////
////        }
//
//        return chain.filter(exchange);
//    }
//}