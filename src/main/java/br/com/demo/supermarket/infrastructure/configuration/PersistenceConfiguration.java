package br.com.demo.supermarket.infrastructure.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfiguration {

    @Bean
    public void objectMapper() {
        ObjectMapper mapper = JsonMapper.builder()
                .build();
        mapper.findAndRegisterModules();
    }
}
