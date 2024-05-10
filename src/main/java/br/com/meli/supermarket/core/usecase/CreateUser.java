package br.com.archdemo.supermarket.core.usecase;

import br.com.archdemo.supermarket.core.domain.entity.User;
import br.com.archdemo.supermarket.core.usecase.contract.IdGenerator;
import br.com.archdemo.supermarket.core.usecase.contract.PasswordEncoder;
import br.com.archdemo.supermarket.core.usecase.contract.UserRepository;
import br.com.archdemo.supermarket.core.usecase.validation.UserValidator;
import br.com.archdemo.supermarket.infrastructure.model.UserModel;

public final class CreateUser {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final IdGenerator idGenerator;

    public CreateUser(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            IdGenerator idGenerator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.idGenerator = idGenerator;
    }

    public User create(final User user) {
        UserValidator.validateUserForCreate(user);
        UserValidator.validateUserEmail(user, this.userRepository);
        UserValidator.validateUserTaxId(user);

        String password = passwordEncoder.encode(user.getPrimaryEmail() + user.getPassword());

        User newUser = UserModel.builder()
                .id(idGenerator.generate())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .taxId(user.getTaxId())
                .primaryEmail(user.getPrimaryEmail())
                .secondaryEmail(user.getSecondaryEmail())
                .password(passwordEncoder.encode(password))
                .build();

        return userRepository.create(newUser);
    }
}
