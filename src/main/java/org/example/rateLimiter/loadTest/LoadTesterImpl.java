package org.example.rateLimiter.loadTest;

import org.example.rateLimiter.RateLimiter;

import java.util.random.RandomGenerator;

public class LoadTesterImpl implements LoadTester {

    private final RateLimiter rateLimiter;

    public LoadTesterImpl(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @Override
    public void test() {
        Thread thread1 = new Thread(getConsumerRunnable(rateLimiter));
        Thread thread2 = new Thread(getConsumerRunnable(rateLimiter));

        thread1.start();
        thread2.start();
    }

    private Runnable getConsumerRunnable(RateLimiter rateLimiter) {
        return () -> {
            while (true) {
                if (rateLimiter.getRateLimit("", 1)) {
                    System.out.println("Got Rate Limit by thread: " + Thread.currentThread().getName());
                }

                try {
                    long randomTime = Math.abs(RandomGenerator.getDefault().nextInt()) % 10 + 1;
                    Thread.sleep(randomTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
