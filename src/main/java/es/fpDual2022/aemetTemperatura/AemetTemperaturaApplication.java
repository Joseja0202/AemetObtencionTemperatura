package es.fpDual2022.aemetTemperatura;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AemetTemperaturaApplication {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		SpringApplication.run(AemetTemperaturaApplication.class, args);

	}

}
