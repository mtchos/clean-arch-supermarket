package br.com.meli.supermarket.presenter.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.meli.supermarket.core.usecase.CreateUser;
import br.com.meli.supermarket.core.usecase.FindUser;
import br.com.meli.supermarket.core.usecase.UpdateUser;
import br.com.meli.supermarket.core.usecase.contract.IdGenerator;
import br.com.meli.supermarket.core.usecase.contract.PasswordEncoder;
import br.com.meli.supermarket.infrastructure.api.BcryptEncoder;
import br.com.meli.supermarket.infrastructure.api.UuidGenerator;
import br.com.meli.supermarket.infrastructure.repository.UserRepositoryImpl;

@Component
public class PresenterConfiguration {

    private final UserRepositoryImpl userRepository;

    private final PasswordEncoder bcryptEncoder = new BcryptEncoder();

    private final IdGenerator uuidGenerator = new UuidGenerator();

    @Autowired
    public PresenterConfiguration(UserRepositoryImpl repository) {
        this.userRepository = repository;
    }

    public CreateUser createUser() {
        return new CreateUser(this.userRepository, bcryptEncoder, uuidGenerator);
    }

    public FindUser findUser() {
        return new FindUser(this.userRepository);
    }

    public UpdateUser updateUser() {
        return new UpdateUser(this.userRepository);
    }
}
