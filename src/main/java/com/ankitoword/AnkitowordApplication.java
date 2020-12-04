package com.ankitoword;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class AnkitowordApplication {

	// O Spring irá fornecer um builder pronto
	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		// Criar uma instância de WebClient recebendo uma base url
        // Todas as requisições irão concatenar com a base url
		return builder
			.baseUrl("https://www.dictionaryapi.com/api/v3/references/learners/json/")
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.build();
	}

	@Bean
	public WebClient ankiWebClient(WebClient.Builder builder) {		
		return builder
			//.baseUrl("http://localhost:8765/")
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(AnkitowordApplication.class, args);
	}

}
