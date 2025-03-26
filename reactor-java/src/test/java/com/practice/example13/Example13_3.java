package com.practice.example13;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class Example13_3 {

    @Test
    public void sayHelloTest() {
        StepVerifier
                .create(GeneralTestExample.sayHello())
                .expectSubscription().as("# expect subscription")
                .expectNext("Hi").as("# expect Hi")
                .expectNext("Reactor").as("# expect Reactor")
                .verifyComplete();
    }
}

/*
expectation "# expect Hi" failed (expected value: Hi; actual value: Hello)
java.lang.AssertionError: expectation "# expect Hi" failed (expected value: Hi; actual value: Hello)
	at reactor.test.MessageFormatter.assertionError(MessageFormatter.java:115)
	...
 */