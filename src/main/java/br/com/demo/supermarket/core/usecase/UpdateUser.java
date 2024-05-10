package br.com.demo.supermarket.core.usecase;

import br.com.demo.supermarket.core.domain.entity.User;
import br.com.demo.supermarket.core.usecase.contract.UserRepository;

public final class UpdateUser {

    private final UserRepository userRepository;

    public UpdateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User update(String id, User userModel) {
        return this.userRepository.update(id, userModel);
    }
}
