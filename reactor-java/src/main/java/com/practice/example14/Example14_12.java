package com.practice.example14;

import com.practice.SampleData;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.util.function.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Slf4j
public class Example14_12 {
    static int SIZE = 0;
    static int COUNT = -1;
    final static List<Integer> DATA_SOURCE = IntStream.rangeClosed(1, 10).boxed().toList();

    public static void main(String[] args) throws InterruptedException {
        log.info("# start");

        Flux.create((FluxSink<Integer> sink) -> {
            sink.onRequest(n -> {
                try {
                    Thread.sleep(1000L);
                    LongStream.range(0, n).boxed()
                            .forEach(_ -> {
                                if (COUNT >= 9) {
                                    sink.complete();
                                } else {
                                    COUNT++;
                                    sink.next(DATA_SOURCE.get(COUNT));
                                }
                            });
                } catch (InterruptedException ignore) {}
            });

            sink.onDispose(() -> log.info("# clean up"));
        }).subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(2);
            }

            @Override
            protected void hookOnNext(Integer value) {
                SIZE++;
                log.info("# onNext: {}", value);

                if (SIZE == 2) {
                    request(2);
                    SIZE = 0;
                }
            }

            @Override
            protected void hookOnComplete() {
                log.info("# onComplete");
            }
        });
    }

}

/*
06:06:13.429 [main] INFO com.practice.example14.Example14_12 -- # start
06:06:14.472 [main] INFO com.practice.example14.Example14_12 -- # onNext: 1
06:06:14.473 [main] INFO com.practice.example14.Example14_12 -- # onNext: 2
06:06:15.473 [main] INFO com.practice.example14.Example14_12 -- # onNext: 3
06:06:15.473 [main] INFO com.practice.example14.Example14_12 -- # onNext: 4
06:06:16.474 [main] INFO com.practice.example14.Example14_12 -- # onNext: 5
06:06:16.474 [main] INFO com.practice.example14.Example14_12 -- # onNext: 6
06:06:17.475 [main] INFO com.practice.example14.Example14_12 -- # onNext: 7
06:06:17.475 [main] INFO com.practice.example14.Example14_12 -- # onNext: 8
06:06:18.475 [main] INFO com.practice.example14.Example14_12 -- # onNext: 9
06:06:18.475 [main] INFO com.practice.example14.Example14_12 -- # onNext: 10
06:06:19.476 [main] INFO com.practice.example14.Example14_12 -- # onComplete
06:06:19.477 [main] INFO com.practice.example14.Example14_12 -- # clean up
 */