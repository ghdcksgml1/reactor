package com.practice.example14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Example14_15 {

    public static void main(String[] args) throws InterruptedException {
        Flux
                .range(1, 20)
                .filter(num -> num % 2 != 0)
                .subscribe(data -> log.info(" onNext: {}", data));
    }

}
