package com.practice.example14;

import com.practice.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Example14_29 {

    public static void main(String[] args) throws InterruptedException {
        final double buyPrice = 50_000_000;

        Flux
                .just("Good", "Bad")
                .flatMap(feeling -> Flux
                        .just("Morning", "Afternoon", "Evening")
                        .map(time -> feeling + " " + time))
                .subscribe(log::info);

    }

}
