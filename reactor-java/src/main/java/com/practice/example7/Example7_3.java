package com.practice.example7;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;

@Slf4j
public class Example7_3 {
    public static void main(String[] args) throws InterruptedException {
        URI worldTimeUri = UriComponentsBuilder.newInstance().scheme("http")
                .host("worldtimeapi.org")
                .port(80)
                .path("/api/timezone/Asia/Seoul")
                .build()
                .encode()
                .toUri();

        Mono<String> mono = getWorldTime(worldTimeUri).cache();
        mono.subscribe(dateTime -> log.info("#dateTime 1: {}", dateTime));
        Thread.sleep(2000);

        mono.subscribe(dateTime -> log.info("#dateTime 2: {}", dateTime));
        Thread.sleep(2000);
    }

    private static Mono<String> getWorldTime(URI worldTimeUri) {
        return WebClient.create()
                .get()
                .uri(worldTimeUri)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume((throwable) -> getDatetimeJsonString())
                .map(response -> {
                    DocumentContext jsonContext = JsonPath.parse(response);
                    return jsonContext.read("$.datetime");
                });
    }

    private static Mono<String> getDatetimeJsonString() {
        return Mono.just("{\"datetime\": \""+ LocalDateTime.now() + "\"}");
    }
}
