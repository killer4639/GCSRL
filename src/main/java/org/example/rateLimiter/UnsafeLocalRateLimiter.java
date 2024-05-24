package org.example.rateLimiter;

public class UnsafeLocalRateLimiter implements RateLimiter {

    private final long timeLimitInMillis;
    private final long maxToken;

    private long currentLimit;
    private long lastUsedTime;

    public UnsafeLocalRateLimiter(long timeLimitInMillis, long maxToken) {
        this.timeLimitInMillis = timeLimitInMillis;
        this.maxToken = maxToken;
        this.lastUsedTime = System.currentTimeMillis() / timeLimitInMillis;
        this.currentLimit = maxToken;
    }

    @Override
    public synchronized boolean getRateLimit(String key, int tokenCount) {
        final long currentTime = System.currentTimeMillis() / timeLimitInMillis;
        if (currentTime > lastUsedTime) {
            lastUsedTime = currentTime;
            currentLimit = maxToken;
        }

        if (currentLimit >= tokenCount) {
            currentLimit -= tokenCount;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean getRateLimit(String key, int tokenCount, long waitInMillis) {
        return false;
    }
}
