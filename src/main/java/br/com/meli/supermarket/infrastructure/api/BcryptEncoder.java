package br.com.archdemo.supermarket.infrastructure.api;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.archdemo.supermarket.core.usecase.contract.PasswordEncoder;

@Component
public class BcryptEncoder implements PasswordEncoder {

    @Override
    public String encode(String string) {
        return new BCryptPasswordEncoder().encode(string);
    }
}
