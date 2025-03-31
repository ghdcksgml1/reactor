package com.practice.example14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
public class Example14_38 {

    public static void main(String[] args) throws InterruptedException {
        Mono
                .just("Task 1")
                .delayElement(Duration.ofSeconds(1))
                .doOnNext(data -> log.info("# Mono doOnNext: {}", data))
                .and(
                        Flux
                                .just("Task 2", "Task 3")
                                .delayElements(Duration.ofMillis(600))
                                .doOnNext(data -> log.info("# Flux doOnNext: {}", data))
                )
                .subscribe(
                        data -> log.info("# onNext: {}", data),
                        error -> log.error("# onError: {}", error.getLocalizedMessage()),
                        () -> log.info("# onComplete")
                );

        Thread.sleep(5000L);
    }

}

/*
12:06:55.892 [parallel-2] INFO com.practice.example14.Example14_38 -- # Flux doOnNext: Task 2
12:06:56.287 [parallel-1] INFO com.practice.example14.Example14_38 -- # Mono doOnNext: Task 1
12:06:56.495 [parallel-3] INFO com.practice.example14.Example14_38 -- # Flux doOnNext: Task 3
12:06:56.496 [parallel-3] INFO com.practice.example14.Example14_38 -- # onComplete
 */