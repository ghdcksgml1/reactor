package com.practice.example14;

import com.practice.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Example14_28 {

    public static void main(String[] args) throws InterruptedException {
        final double buyPrice = 50_000_000;

        Flux
                .fromIterable(SampleData.getCoins())
                .filter(tuple -> tuple.getT2() > 100_000)
                .doOnNext(data -> log.info("# doOnNext: {}", data))
                .map(tuple -> calculateProfitRate(buyPrice, tuple.getT2()))
                .subscribe(data -> log.info("# onNext: {}%", data));

    }

    private static double calculateProfitRate(final double buyPrice, Long topPrice) {
        return (topPrice - buyPrice) / buyPrice * 100;
    }

}
