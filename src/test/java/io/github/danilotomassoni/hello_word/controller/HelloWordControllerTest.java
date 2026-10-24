package io.github.danilotomassoni.hello_word.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HelloWordController.class)
@TestPropertySource(properties = "server.port=8080")
class HelloWordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnHelloWorld() throws Exception {
        mockMvc.perform(get("/api/v1/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));
    }
}
