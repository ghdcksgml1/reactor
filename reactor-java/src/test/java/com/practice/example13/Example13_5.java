package com.practice.example13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class Example13_5 {

    @Test
    public void takeNumberTest() {
        Flux<Integer> source = Flux.range(0, 1000);
        StepVerifier
                .create(GeneralTestExample.takeNumber(source, 500),
                        StepVerifierOptions.create().scenarioName("Verify from 0 to 499"))
                .expectSubscription()
                .expectNext(0)
                .expectNextCount(499)
                .expectNext(500)
                .expectComplete()
                .verify();
    }
}

/*
[Verify from 0 to 499] expectation "expectNext(500)" failed (expected: onNext(500); actual: onComplete())
 */