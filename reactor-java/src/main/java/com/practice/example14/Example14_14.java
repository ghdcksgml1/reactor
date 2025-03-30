package com.practice.example14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Random;
import java.util.stream.IntStream;

@Slf4j
public class Example14_14 {
    static int start = 1;
    static int end = 4;

    public static void main(String[] args) throws InterruptedException {
        Flux.create((FluxSink<Integer> sink) -> {
                    sink.onRequest(n -> {
                        log.info("# requested: " + n);
                        try {
                            Thread.sleep(500L);
                            IntStream.rangeClosed(start, end).forEach(sink::next);

                            start += 4;
                            end += 4;
                        } catch (InterruptedException ignore) {
                        }
                    });

                    sink.onDispose(() -> log.info("# clean up"));
                }, FluxSink.OverflowStrategy.DROP)
                .subscribeOn(Schedulers.boundedElastic())
                .publishOn(Schedulers.parallel(), 2)
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(3000L);
    }

}
