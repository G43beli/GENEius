package ch.fhnw.mscmi.geneservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@SpringBootApplication
@OpenAPIDefinition
public class GeneserviceApplication {
	private static final Logger logger = LoggerFactory.getLogger(GeneserviceApplication.class);

	public static void main(String[] args) {
		logger.info("GENEius: Application is starting!");
		SpringApplication.run(GeneserviceApplication.class, args);
	}

}
