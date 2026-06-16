package com.queueflow;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class QueueflowApplication {

	@Value("${server.port:8080}")
	private String port;

	public static void main(String[] args) {
		SpringApplication.run(QueueflowApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {

		String swaggerUrl =
				"http://localhost:" + port + "/swagger-ui/index.html";

		String apiDocsUrl =
				"http://localhost:" + port + "/v3/api-docs";

		System.out.println("""
                
                ==================================================
                         QUEUEFLOW STARTED SUCCESSFULLY
                ==================================================
                
                Swagger UI :
                %s
                
                OpenAPI :
                %s
                
                ==================================================
                """
				.formatted(swaggerUrl, apiDocsUrl));
	}
}