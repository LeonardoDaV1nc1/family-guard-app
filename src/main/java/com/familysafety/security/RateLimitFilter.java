package com.familysafety.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private final Map<String, RequestInfo> requestCounts = new ConcurrentHashMap<>();
    
    private static final int MAX_REQUESTS = 5; 
    private static final long TIME_WINDOW_MS = 60 * 1000; 

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        if (request.getRequestURI().contains("/api/users/login")) {
            String clientIp = getClientIP(request);
            long currentTime = Instant.now().toEpochMilli();

            requestCounts.compute(clientIp, (ip, info) -> {
                if (info == null || currentTime - info.startTime > TIME_WINDOW_MS) {
                    return new RequestInfo(currentTime, 1);
                } else {
                    info.count++;
                    return info;
                }
            });

            RequestInfo info = requestCounts.get(clientIp);
            if (info.count > MAX_REQUESTS) {
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value()); 
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Muitas tentativas de login. Tente novamente mais tarde.\"}");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    private static class RequestInfo {
        long startTime;
        int count;

        RequestInfo(long startTime, int count) {
            this.startTime = startTime;
            this.count = count;
        }
    }
}