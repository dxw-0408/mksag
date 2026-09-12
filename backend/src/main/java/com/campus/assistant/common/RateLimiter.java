package com.campus.assistant.common;

import com.campus.assistant.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/** 限流：优先 Redis 固定窗口计数，Redis 不可用时降级为内存计数。 */
@Component
@RequiredArgsConstructor
public class RateLimiter {

    private static final int DEFAULT_LIMIT = 60;
    private static final int DEFAULT_WINDOW_SECONDS = 60;

    private final CacheService cacheService;
    private final Map<String, List<Long>> memory = new ConcurrentHashMap<>();

    public void check(String key) {
        check(key, DEFAULT_LIMIT, DEFAULT_WINDOW_SECONDS);
    }

    public void check(String key, int limit, int windowSeconds) {
        String redisKey = redisKey(key, windowSeconds);
        Long count = cacheService.increment(redisKey);
        if (count != null) {
            if (count == 1) {
                cacheService.expire(redisKey, windowSeconds);
            }
            if (count > limit) {
                throw new BizException(BusinessCode.RATE_LIMIT);
            }
        } else {
            checkMemory(key, limit, windowSeconds);
        }
    }

    private String redisKey(String key, int windowSeconds) {
        long window = System.currentTimeMillis() / 1000 / windowSeconds;
        return "ratelimit:" + key + ":" + window;
    }

    private synchronized void checkMemory(String key, int limit, int windowSeconds) {
        long now = System.currentTimeMillis();
        long windowMs = windowSeconds * 1000L;
        List<Long> bucket = memory.computeIfAbsent(key, k -> new ArrayList<>());
        bucket.removeIf(t -> now - t > windowMs);
        if (bucket.size() >= limit) {
            throw new BizException(BusinessCode.RATE_LIMIT);
        }
        bucket.add(now);
    }
}
