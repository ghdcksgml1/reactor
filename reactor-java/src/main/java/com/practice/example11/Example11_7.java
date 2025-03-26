package com.practice.example11;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.ContextView;

@Slf4j
public class Example11_7 {
    private static final String key1 = "company";

    public static void main(String[] args) throws InterruptedException {
        Mono.just("Steve")
//                .transformDeferredContextual((stringMono, contextView) -> contextView.get("role"))
                .flatMap(name ->
                        Mono.deferContextual(ctx ->
                                Mono.just(ctx.get(key1) + ", " + name)
                                        .transformDeferredContextual(((stringMono, contextView) ->
                                                stringMono.map(data -> data + ", " + contextView.get("role"))
                                        ))
                                        .contextWrite(context -> context.put("role", "CEO"))
                        )
                )
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key1, "Apple"))
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(100L);
    }

    private static Mono<String> printContext(ContextView ctx) {
        return Mono.just("Company: " + ctx.get(key1));
    }
}
