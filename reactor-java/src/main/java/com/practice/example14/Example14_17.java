package com.practice.example14;

import com.practice.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Example14_17 {

    public static void main(String[] args) throws InterruptedException {
        Flux
                .fromIterable(SampleData.getCoins())
                .filterWhen(coin -> Mono.just(coin.getT2() > 1_000_000)
                        .publishOn(Schedulers.parallel()))
                .publishOn(Schedulers.boundedElastic())
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(1000L);
    }

}
