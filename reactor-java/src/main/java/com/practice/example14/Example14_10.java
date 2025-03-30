package com.practice.example14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

@Slf4j
public class Example14_10 {
    public static void main(String[] args) throws InterruptedException {
        final int dan = 3;

        Flux
                .generate(() -> Tuples.of(dan, 1), (state, sink) -> {
                    sink.next(state.getT1() + " * " + state.getT2() + " = " + state.getT1()  * state.getT2());

                    if (state.getT2() == 9) {
                        sink.complete();
                    }

                    return Tuples.of(state.getT1(), state.getT2() + 1);
                }, state -> log.info("# 구구단 {}단 종료.", state.getT1()))
                .subscribe(data -> log.info("# onNext: {}", data));
    }

}

/*
05:48:17.351 [main] INFO com.practice.example14.Example14_10 -- # onNext: 3 * 1 = 3
05:48:17.353 [main] INFO com.practice.example14.Example14_10 -- # onNext: 3 * 2 = 6
05:48:17.353 [main] INFO com.practice.example14.Example14_10 -- # onNext: 3 * 3 = 9
05:48:17.353 [main] INFO com.practice.example14.Example14_10 -- # onNext: 3 * 4 = 12
05:48:17.353 [main] INFO com.practice.example14.Example14_10 -- # onNext: 3 * 5 = 15
05:48:17.353 [main] INFO com.practice.example14.Example14_10 -- # onNext: 3 * 6 = 18
05:48:17.353 [main] INFO com.practice.example14.Example14_10 -- # onNext: 3 * 7 = 21
05:48:17.353 [main] INFO com.practice.example14.Example14_10 -- # onNext: 3 * 8 = 24
05:48:17.353 [main] INFO com.practice.example14.Example14_10 -- # onNext: 3 * 9 = 27
05:48:17.353 [main] INFO com.practice.example14.Example14_10 -- # 구구단 3단 종료.
 */
