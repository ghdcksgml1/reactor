package com.practice.example14;

import com.practice.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
public class Example14_6 {
    public static void main(String[] args) throws InterruptedException {
        log.info("# start: {}", LocalDateTime.now());
        Mono<LocalDateTime> justMono = Mono.just(LocalDateTime.now());
        Mono<LocalDateTime> deferMono = Mono.defer(() -> Mono.just(LocalDateTime.now()));

        Thread.sleep(2000);

        justMono.subscribe(data -> log.info("# onNext just1: {}", data));
        deferMono.subscribe(data -> log.info("# onNext defer1: {}", data));

        Thread.sleep(2000L);

        justMono.subscribe(data -> log.info("# onNext just2: {}", data));
        deferMono.subscribe(data -> log.info("# onNext defer2: {}", data));
    }
}

/*
05:25:11.942 [main] INFO com.practice.example14.Example14_6 -- # start: 2025-03-30T05:25:11.942239773

05:25:13.972 [main] INFO com.practice.example14.Example14_6 -- # onNext just1: 2025-03-30T05:25:11.944680225
05:25:13.972 [main] INFO com.practice.example14.Example14_6 -- # onNext defer1: 2025-03-30T05:25:13.972330029

05:25:15.973 [main] INFO com.practice.example14.Example14_6 -- # onNext just2: 2025-03-30T05:25:11.944680225
05:25:15.973 [main] INFO com.practice.example14.Example14_6 -- # onNext defer2: 2025-03-30T05:25:15.973500911
 */