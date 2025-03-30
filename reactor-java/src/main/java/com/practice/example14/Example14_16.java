package com.practice.example14;

import com.practice.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Example14_16 {

    public static void main(String[] args) throws InterruptedException {
        Flux
                .fromIterable(SampleData.getCoins())
                .filter(coin -> coin.getT2() >= 20_000_000)
                .subscribe(data -> log.info("# onNext: {}", data));
    }

}
