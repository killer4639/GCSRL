package org.example.rateLimiter;

public interface RateLimiter {

    boolean getRateLimit(String key, int tokenCount);

    boolean getRateLimit(String key, int tokenCount, long waitInMillis);

}
