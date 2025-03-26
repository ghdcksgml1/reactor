package com.practice.example11;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;
import reactor.util.context.ContextView;

@Slf4j
public class Example11_4 {
    private static final String key1 = "company";
    private static final String key2 = "firstName";
    private static final String key3 = "lastName";

    public static void main(String[] args) throws InterruptedException {

        Mono
                .deferContextual(Example11_4::printContext)
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key1, "Apple"))
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(100L);
    }

    private static Mono<String> printContext(ContextView ctx) {
        return Mono.just(ctx.get(key1) + ", " + ctx.getOrEmpty(key2) + " " + ctx.getOrDefault(key3, "no lastName"));
    }
}
