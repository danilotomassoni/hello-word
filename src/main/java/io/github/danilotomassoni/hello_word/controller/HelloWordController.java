package io.github.danilotomassoni.hello_word.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.danilotomassoni.hello_word.service.GreetingService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/hello")
public class HelloWordController {

    private static final Logger log = LoggerFactory.getLogger(HelloWordController.class);

    @Value("${server.port}")
    private String serverPort;

    private final GreetingService greetingService;

    public HelloWordController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping
    public ResponseEntity<String> helloWorld(HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        log.warn("--> Requisição RECEBIDA na instância da porta {} | IP do Cliente: {}", serverPort, clientIp);
        return ResponseEntity.ok(greetingService.getGreeting());
    }
}
