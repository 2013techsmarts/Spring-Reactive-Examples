package org.smarttechie;

import org.smarttechie.model.Product;
import org.smarttechie.susbcriber.BackpressureReadySubscriber;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SpringReactiveClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpringReactiveClientApplication.class);

		app.run(args);
		//backpressure();
	}
	@Override
	public void run(String... args) throws Exception {
		WebClient webClient = WebClient.create("http://localhost:8081");
		webClient.get().uri("/products")
				.retrieve()
				.bodyToFlux(Product.class)
				.log()
				.subscribe(System.out::println);
		System.out.println("===========>>>>>" + Thread.currentThread());
		/*WebClient webClient = WebClient.create("http://localhost:8081");
		BackpressureReadySubscriber<Product> backpressureReadySubscriber = new BackpressureReadySubscriber<>();
		webClient.get().uri("/products")
				.retrieve()
				.bodyToFlux(Product.class)
				.log()
				.subscribe(backpressureReadySubscriber);
		System.out.println("===========>>>>>" + Thread.currentThread());*/
	}

	//public static void backpressure() {

	//}
}
