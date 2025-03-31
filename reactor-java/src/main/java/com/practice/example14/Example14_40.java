package com.practice.example14;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.stream.Collectors;

@Slf4j
public class Example14_40 {

    public static void main(String[] args) throws InterruptedException {
        Flux
                .just("...", "---", "...")
                .map(Example14_40::transformMorseCode)
                .collectList()
                .subscribe(list -> log.info(String.join("", list)));

        Thread.sleep(6000L);
    }

    private static String transformMorseCode(String morseCode) {
        if (morseCode.equals("...")) return "s";
        if (morseCode.equals("---")) return "o";
        return "";
    }
}
