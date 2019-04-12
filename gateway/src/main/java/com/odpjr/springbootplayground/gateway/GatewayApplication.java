package com.odpjr.springbootplayground.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}


	@Bean
	RouteLocator getRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("hello", r -> r.path("/hello/**")
						.filters(f -> f
								.hystrix(c -> c.setName("cmd").setFallbackUri("forward:/fallback"))
								.prefixPath("/api/greeting"))
						.uri("http://localhost:8081"))
				.route("time", r -> r.path("/time/**")
						.filters(f -> f
								.hystrix(c -> c.setName("cmd").setFallbackUri("forward:/fallback"))
								.prefixPath("/api"))
						.uri("http://localhost:8082"))
				.build();

		/*
			http://localhost:8080/hello          -> http://localhost:8081/api/greeting/hello
			http://localhost:8080/hello/world    -> http://localhost:8081/api/greeting/hello/world
			http://localhost:8080/time/timestamp -> http://localhost:8081/api/time/timestamp
			http://localhost:8080/time/dayofweek -> http://localhost:8081/api/time/dayofweek
			http://localhost:8080/time/month     -> http://localhost:8081/api/time/month
		*/
	}

	@RequestMapping("/fallback")
	public String fallback() {
		return "This is fallback";
	}
}
