package org.example.rateLimiter;

/**
 * This class needs to be thread safe as it is going to be used by multiple threads
 */
public class LocalRateLimiter implements RateLimiter {



    @Override
    public boolean getRateLimit(String key, int tokenCount) {
        return false;
    }

    @Override
    public boolean getRateLimit(String key, int tokenCount, long waitInMillis) {
        return false;
    }
}
