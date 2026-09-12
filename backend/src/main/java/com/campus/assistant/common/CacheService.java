package com.campus.assistant.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

/** Redis 缓存封装。Redis 不可用时自动降级为 no-op，不阻断主流程。 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CacheService {

    private final StringRedisTemplate stringRedisTemplate;

    public String get(String key) {
        try {
            return stringRedisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            log.warn("Redis 读取失败，降级为 no-op: {}", e.getMessage());
            return null;
        }
    }

    public void set(String key, String value, long ttlSeconds) {
        try {
            stringRedisTemplate.opsForValue().set(key, value, Duration.ofSeconds(ttlSeconds));
        } catch (Exception e) {
            log.warn("Redis 写入失败，降级为 no-op: {}", e.getMessage());
        }
    }

    public Long increment(String key) {
        try {
            return stringRedisTemplate.opsForValue().increment(key);
        } catch (Exception e) {
            log.warn("Redis 自增失败，降级为 no-op: {}", e.getMessage());
            return null;
        }
    }

    public void expire(String key, long ttlSeconds) {
        try {
            stringRedisTemplate.expire(key, Duration.ofSeconds(ttlSeconds));
        } catch (Exception e) {
            log.warn("Redis 过期设置失败，降级为 no-op: {}", e.getMessage());
        }
    }
}
