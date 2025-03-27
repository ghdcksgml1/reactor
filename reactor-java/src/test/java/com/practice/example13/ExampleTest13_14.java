package com.practice.example13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ExampleTest13_14 {

    @Test
    public void getSecretMessageTest() {
        final String SECRET_KEY = "hello";
        final String BASE64_SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        Mono<String> source = Mono.just(SECRET_KEY);

        StepVerifier
                .create(
                        ContextTestExample
                                .getSecretMessage(source)
                                .contextWrite(context -> context.put("secretMessage", "Hello, Reactor"))
                                .contextWrite(context -> context.put("secretKey", BASE64_SECRET_KEY))
                )
                .expectSubscription()
                .expectAccessibleContext()
                .hasKey("secretKey")
                .hasKey("secretMessage")
                .then()
                .expectNext("Hello, Reactor")
                .expectComplete()
                .verify();
    }
}
