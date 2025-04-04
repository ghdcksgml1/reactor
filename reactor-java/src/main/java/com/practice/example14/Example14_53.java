package com.practice.example14;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class Example14_53 {

    public static void main(String[] args) throws InterruptedException {
        Flux.range(1, 11)
                .window(3)
                .flatMap(flux -> {
                    log.info("=============");
                    return flux;
                })
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        subscription.request(2);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        log.info("# onNext: {}", value);
                        request(2);
                    }
                });
    }

}
