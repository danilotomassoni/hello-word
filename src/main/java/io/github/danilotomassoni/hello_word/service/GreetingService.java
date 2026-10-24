package io.github.danilotomassoni.hello_word.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    @Cacheable("greetings")
    public String getGreeting() {
        return "Hello World!";
    }
}
