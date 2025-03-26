package com.practice.example10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Example10_1 {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(publishInt())
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(data -> log.info("# doOnNext: {}", data))
                .doOnSubscribe(subscription -> log.info("# doOnSubscribe"))
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(500L);
    }

    private static Integer[] publishInt() {
        log.info("publish int.");
        return new Integer[] {1, 3, 5, 7};
    }
}
