package com.practice.example9;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Slf4j
public class Example9_8 {
    public static void main(String[] args) throws InterruptedException {
        Sinks.Many<Object> unicastSink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Object> fluxView = unicastSink.asFlux();

        unicastSink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        unicastSink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> log.info("# Subscriber1: {}", data));

        unicastSink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

//        fluxView.subscribe(data -> log.info("# Subscriber2: {}", data));
    }
}
