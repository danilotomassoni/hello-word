package io.github.danilotomassoni.hello_word;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;

@SpringBootTest
class HelloWordApplicationTests {

	@Autowired
	private CacheManager cacheManager;

	@Test
	void contextLoads() {
	}

	@Test
	void shouldConfigureRedisCacheManager() {
		assertInstanceOf(RedisCacheManager.class, cacheManager);
	}
}
