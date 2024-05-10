package br.com.demo.supermarket.infrastructure.api;

import org.springframework.stereotype.Component;

import java.util.UUID;

import br.com.demo.supermarket.core.usecase.contract.IdGenerator;

@Component
public class UuidGenerator implements IdGenerator {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
