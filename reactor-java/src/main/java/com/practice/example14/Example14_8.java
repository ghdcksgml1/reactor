package com.practice.example14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Slf4j
public class Example14_8 {
    public static void main(String[] args) throws InterruptedException {
        Path path = Paths.get("./example/using_example.txt");

        Flux
                .using(() -> Files.lines(path), Flux::fromStream, Stream::close)
                .subscribeOn(Schedulers.parallel())
                .subscribe(log::info);

        Thread.sleep(1000L);
    }

}
