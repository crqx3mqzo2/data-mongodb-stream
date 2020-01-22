package com.apt.data.mongodb.stream.app;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@SpringBootApplication
public class SpringMongodbStreamApplication implements CommandLineRunner {

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(SpringMongodbStreamApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringMongodbStreamApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Flux<String> nombres = Flux.just("alfonso", "lusana", "ana", "maria").doOnNext(e -> {
			if (e.isEmpty()) {
				throw new RuntimeException("No puede venir nombres vacíos");
			}
			{
				System.out.println(e);
			}
		});
		nombres.subscribe(e -> log.info(e), error -> log.error(error.getMessage()), new Runnable() {

			@Override
			public void run() {
				System.out.println("ha finalizado la ejecucióncon éxito!");
			}
		});

	}

}
