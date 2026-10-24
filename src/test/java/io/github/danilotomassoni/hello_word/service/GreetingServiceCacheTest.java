package io.github.danilotomassoni.hello_word.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.SimpleKey;

@SpringBootTest(properties = "spring.cache.type=simple")
class GreetingServiceCacheTest {

    @Autowired
    private GreetingService greetingService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void shouldCacheGreeting() {
        assertEquals("Hello World!", greetingService.getGreeting());

        var greetingCache = cacheManager.getCache("greetings");
        assertNotNull(greetingCache);
        var cachedGreeting = greetingCache.get(SimpleKey.EMPTY);

        assertNotNull(cachedGreeting);
        assertEquals("Hello World!", cachedGreeting.get());
    }
}
