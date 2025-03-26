package com.practice.example9;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Slf4j
public class Example9_9 {
    public static void main(String[] args) throws InterruptedException {
        Sinks.Many<Object> multicastSink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<Object> fluxView = multicastSink.asFlux();

        multicastSink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        multicastSink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> log.info("# Subscriber1: {}", data));
        fluxView.subscribe(data -> log.info("# Subscriber2: {}", data));

        multicastSink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);
        multicastSink.emitNext(4, Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
