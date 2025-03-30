package com.practice.example14;

import com.practice.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class Example14_11 {
    public static void main(String[] args) throws InterruptedException {
        List<Tuple2<String, Long>> coins = SampleData.getCoins();

        Flux
                .generate(() -> 0, (state, sink) -> {
                    if (state >= coins.size()) {
                        sink.complete();
                    } else {
                        sink.next(coins.get(state));
                    }

                    return ++state;
                })
                .subscribe(coin -> log.info("{}", coin));
    }

}
