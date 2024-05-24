package org.example;

import org.example.rateLimiter.RateLimiter;
import org.example.rateLimiter.UnsafeLocalRateLimiter;
import org.example.rateLimiter.loadTest.LoadTester;
import org.example.rateLimiter.loadTest.LoadTesterImpl;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        RateLimiter rateLimiter = new UnsafeLocalRateLimiter(TimeUnit.SECONDS.toMillis(5), 10);

        LoadTester loadTester = new LoadTesterImpl(rateLimiter);

        loadTester.test();
    }
}