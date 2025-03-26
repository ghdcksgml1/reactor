package com.practice.example6;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Example6_7 {
    public static void main(String[] args) {
        Flux.concat(
                Flux.just("Mercury", "Venus", "Earth"),
                Flux.just("Mars", "Jupiter", "Saturn"),
                Flux.just("Uranus", "Neptune", "Pluto")
        )
                .collectList()
                .subscribe(planets -> System.out.println(planets));
    }
}
