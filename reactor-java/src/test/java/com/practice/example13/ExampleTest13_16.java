package com.practice.example13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;

public class ExampleTest13_16 {

    @Test
    public void getCityTest() {
        StepVerifier
                .create(RecordTestExample.getCapitalizedCountry(
                        Flux.just("korea", "england", "canada", "india")))
                .expectSubscription()
                .recordWith(ArrayList::new)
                .thenConsumeWhile(country -> !country.isEmpty())
                .consumeRecordedWith(countries -> {
                    Boolean result = countries.stream()
                                    .allMatch(country -> Character.isUpperCase(country.charAt(0)));
                    assert result;
                })
                .verifyComplete();
    }
}
