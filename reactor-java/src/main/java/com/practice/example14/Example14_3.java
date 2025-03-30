package com.practice.example14;

import com.practice.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Example14_3 {
    public static void main(String[] args) {
        Flux
                .fromStream(SampleData.getCoins().stream())
                .filter(coin -> coin.getT1().equals("BTC") || coin.getT1().equals("ETH"))
                .subscribe(coin -> log.info("coin 명: {}, 현재가: {}", coin.getT1(), coin.getT2()));
    }
}
