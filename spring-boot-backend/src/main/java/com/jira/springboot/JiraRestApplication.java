package com.jira.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@SpringBootApplication
@RestController
public class JiraRestApplication {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(JiraRestApplication.class, args);

		System.out.println("Let's inspect the beans provided by Spring Boot:");

		//String[] beanNames = ctx.getBeanDefinitionNames();

		//Arrays.stream(beanNames).sorted().forEach(System.out::println);
	}
}
