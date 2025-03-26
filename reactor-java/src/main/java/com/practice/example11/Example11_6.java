package com.practice.example11;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.ContextView;

@Slf4j
public class Example11_6 {
    private static final String key1 = "company";
    private static final String key2 = "name";

    public static void main(String[] args) throws InterruptedException {
        Mono.deferContextual(Example11_6::printContext)
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key2, "Bill"))
                .transformDeferredContextual((mono, ctx) ->
                        mono.map(data -> data + ", " + ctx.getOrDefault(key2, "Steve"))
                )
                .contextWrite(context -> context.put(key1, "Apple"))
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(100L);
    }

    private static Mono<String> printContext(ContextView ctx) {
        return Mono.just("Company: " + ctx.get(key1));
    }
}
