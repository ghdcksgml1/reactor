package com.practice.example14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.zip.DataFormatException;

@Slf4j
public class Example14_45 {

    public static void main(String[] args) throws InterruptedException {
        getBooks()
                .map(book -> book.toUpperCase())
                .onErrorReturn("No pen name")
                .subscribe(log::info);
    }

    public static Flux<String> getBooks() {
        return Flux.fromArray(new String[]{"TOM-BOY", "GRACE-GIRL", "DAVID-BOY", "TOM-BOY", "KEVIN-BOY", "TOM-BOY", "GRACE-GIRL", null});
    }
}
