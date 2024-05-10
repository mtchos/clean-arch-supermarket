package br.com.archdemo.supermarket.core.usecase;

import br.com.archdemo.supermarket.core.domain.entity.User;
import br.com.archdemo.supermarket.core.usecase.contract.UserRepository;

public final class UpdateUser {

    private final UserRepository userRepository;

    public UpdateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User update(String id, User userModel) {
        return this.userRepository.update(id, userModel);
    }
}
