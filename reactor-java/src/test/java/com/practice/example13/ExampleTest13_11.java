package com.practice.example13;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class ExampleTest13_11 {

    @Test
    public void generateNumberTest() {
        StepVerifier
                .create(BackPressureTestExample.generateNumber(), 99L)
                .thenConsumeWhile(num -> num >= 1)
                .verifyComplete();
    }
}
