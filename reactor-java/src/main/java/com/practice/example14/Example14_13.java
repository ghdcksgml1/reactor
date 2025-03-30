package com.practice.example14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Slf4j
public class Example14_13 {
    public static void main(String[] args) throws InterruptedException {
        CryptoCurrencyPriceEmitter priceEmitter = new CryptoCurrencyPriceEmitter();

        Flux.create((FluxSink<Integer> sink) ->
                priceEmitter.setListener(new CryptoCurrencyPriceListener() {
                    @Override
                    public void onPrice(Flux<Integer> priceList) {
                        priceList.subscribe(price -> {
                            sink.next(price);
                        });
                    }

                    @Override
                    public void onComplete() {
                        sink.complete();
                    }
                }))
                .publishOn(Schedulers.parallel())
                .subscribe(
                        data -> log.info("# onNext: {}", data),
                        error -> {},
                        () -> log.info("# onComplete")
                );

        Thread.sleep(3000L);

        priceEmitter.flowInto();

        Thread.sleep(2000L);
        priceEmitter.complete();
    }

    private static class CryptoCurrencyPriceEmitter {
        private CryptoCurrencyPriceListener listener;

        public void setListener(CryptoCurrencyPriceListener listener) {
            this.listener = listener;
        }

        public void flowInto() {
            Flux<Integer> flux = Flux.interval(Duration.ofMillis(100))
                    .flatMap(_ -> Mono.defer(() -> Mono.just(new Random().nextInt(10_000_000))))
                    .subscribeOn(Schedulers.parallel());
            this.listener.onPrice(flux);
        }

        public void complete() {
            this.listener.onComplete();
        }
    }

    private static interface CryptoCurrencyPriceListener {
        void onPrice(Flux<Integer> priceList);
        void onComplete();
    }
}
