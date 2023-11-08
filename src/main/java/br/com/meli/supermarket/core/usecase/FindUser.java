package br.com.meli.supermarket.core.usecase;

import java.util.List;

import br.com.meli.supermarket.core.domain.entity.User;
import br.com.meli.supermarket.core.usecase.contract.UserRepository;

public final class FindUser {

    private final UserRepository userRepository;

    public FindUser(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(final String id) {
        return userRepository.findById(id);
    }

    public User findByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    public List<? extends User> findAll() {
        return userRepository.findAll();
    }

    public List<? extends User> findAll(String name) {
        return userRepository.findAll(name);
    }
}
