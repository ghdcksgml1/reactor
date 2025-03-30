package com.practice.example14;

import com.practice.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Example14_24 {

    public static void main(String[] args) throws InterruptedException {
        Flux
                .fromIterable(SampleData.getCoins())
                .takeUntil(tuple -> tuple.getT2() > 20_000_000)
                .subscribe(tuple -> log.info("# onNext: {}, {}", tuple.getT1(), tuple.getT2()));

        Thread.sleep(3000L);
    }

}
