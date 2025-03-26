package com.practice.example11;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.ContextView;

@Slf4j
public class Example11_5 {
    private static final String key1 = "company";

    public static void main(String[] args) throws InterruptedException {
        Mono<String> mono = Mono.deferContextual(Example11_5::printContext)
                .publishOn(Schedulers.parallel());

        mono.contextWrite(context -> context.put(key1, "Apple"))
                        .subscribe(data -> log.info("$ subscribe1 onNext: {}", data));

        mono.contextWrite(context -> context.put(key1, "Microsoft"))
                .subscribe(data -> log.info("$ subscribe2 onNext: {}", data));

        Thread.sleep(100L);
    }

    private static Mono<String> printContext(ContextView ctx) {
        return Mono.just("Company: " + ctx.get(key1));
    }
}
