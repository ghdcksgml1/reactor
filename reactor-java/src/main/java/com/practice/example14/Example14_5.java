package com.practice.example14;

import com.practice.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Example14_5 {
    public static void main(String[] args) {
        Flux
                .range(1, 3)
                .map(idx -> SampleData.getCoins().get(idx))
                .subscribe(data -> log.info("{}",data));
    }
}
