package com.practice.example14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class Example14_51 {

    public static void main(String[] args) throws InterruptedException {
        Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(1))
                .elapsed()
                .subscribe(data -> log.info("# onNext: {}, time: {}", data.getT2(), data.getT1()));

        Thread.sleep(6000);
    }

}
