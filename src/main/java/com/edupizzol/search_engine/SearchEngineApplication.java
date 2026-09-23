package com.edupizzol.search_engine;
import com.edupizzol.search_engine.service.IndexingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SearchEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchEngineApplication.class, args);
	}

	@Bean
	public CommandLineRunner testIndexing(IndexingService indexingService) {
		return args -> {
			indexingService.indexSeed("https://pt.wikipedia.org/wiki/Java_(linguagem_de_programa%C3%A7%C3%A3o)");
			System.out.println("Indexação concluída!");
		};
	}
}