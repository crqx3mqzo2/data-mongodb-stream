package com.apt.data.mongodb.stream.app;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import reactor.core.publisher.Flux;

@RestController
public class ChangesController {

	Flux<String> nombres = Flux.just("");
	Flux<Object> nameRequest;

	@GetMapping("/emit-data-sets")
	public SseEmitter fetchData2() throws IOException {
		SseEmitter emitter = new SseEmitter();
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(() -> {
			nombres.doOnNext(e -> {
				if (e.isEmpty()) {
					throw new RuntimeException("sfsfsf");
				}

			});
			nameRequest.subscribe(e -> System.out.println(e), error -> System.out.println(error.getCause()),
					new Runnable() {

						@Override
						public void run() {
							System.out.println("ha finalizado la ejecucióncon éxito!");
						}
					});
		});
		executor.shutdown();
		return emitter;
	}

	@PostMapping("/data-sets")
	public String ddasd(@RequestBody adad as) {
		List<Object> a = Arrays.asList(as);
		nameRequest = Flux.fromIterable(a);
		return "";
	}

	class adad {
		List<String> a;

		public List<String> getA() {
			return a;
		}

		public void setA(List<String> a) {
			this.a = a;
		}
		
		
	}

}
