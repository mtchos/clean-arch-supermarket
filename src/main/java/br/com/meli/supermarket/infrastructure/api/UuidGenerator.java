package br.com.meli.supermarket.infrastructure.api;

import org.springframework.stereotype.Component;

import java.util.UUID;

import br.com.meli.supermarket.core.usecase.contract.IdGenerator;

@Component
public class UuidGenerator implements IdGenerator {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
