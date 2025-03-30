package com.practice.example14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Slf4j
public class Example14_9 {
    public static void main(String[] args) throws InterruptedException {
        Flux
                .generate(() -> 0, (state, sink) -> {
                    sink.next(state);
                    if (state == 10) {
                        sink.complete();
                    }
                    return ++state;
                })
                .doOnNext(data -> log.info("{}", data))
                .publishOn(Schedulers.boundedElastic())
                .subscribeOn(Schedulers.parallel())
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(1000L);
    }

}
