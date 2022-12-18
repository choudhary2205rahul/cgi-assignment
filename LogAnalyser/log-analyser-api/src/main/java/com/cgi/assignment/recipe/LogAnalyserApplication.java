package com.cgi.assignment.recipe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Slf4j
public class LogAnalyserApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogAnalyserApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4200")
						.allowCredentials(true)
						.allowedMethods("GET", "POST", "OPTIONS","PATCH", "DELETE","PUT")
						.allowedHeaders("*");
			}
		};
	}

}
