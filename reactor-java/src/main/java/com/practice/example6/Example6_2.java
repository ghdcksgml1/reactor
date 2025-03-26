package com.practice.example6;

import reactor.core.publisher.Mono;

public class Example6_2 {
    public static void main(String[] args) {
        Mono
                .just("Hello")
                .subscribe(
                        none -> System.out.println("# emitted onNext signal"),
                        error -> {},
                        () -> System.out.println("# emitted onComplete signal")
                );
    }
}