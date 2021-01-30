package ir.sk.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by sad.kayvanfar
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate getCustomRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
   //     restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate;
    }

}

