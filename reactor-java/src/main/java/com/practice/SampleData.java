package com.practice;

import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.List;

public class SampleData {

    public static List<Tuple2<String, Long>> getCoins() {
        return List.of(
                Tuples.of("BTC", 52_000_000L),
                Tuples.of("ETH", 1_720_000L),
                Tuples.of("XRP", 533L),
                Tuples.of("ICX", 2_080L),
                Tuples.of("EOS", 4_020L),
                Tuples.of("BCH", 558_000L)
        );
    }
}
