package br.com.meli.supermarket.core.usecase;

import br.com.meli.supermarket.core.domain.entity.User;
import br.com.meli.supermarket.core.usecase.contract.IdGenerator;
import br.com.meli.supermarket.core.usecase.contract.PasswordEncoder;
import br.com.meli.supermarket.core.usecase.contract.UserRepository;
import br.com.meli.supermarket.core.usecase.exception.UserValidationException;
import br.com.meli.supermarket.core.usecase.validation.UserValidator;
import br.com.meli.supermarket.infrastructure.model.UserModel;

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
        UserValidator.validateCreateUser(user);

        User userFromRepository = null;
        try {
            userFromRepository = this.userRepository.findByEmail(user.getPrimaryEmail());
        } catch (Exception ignored) {

        }

        if (userFromRepository != null) {
            throw new UserValidationException("E-mail is already being used");
        }

        String password = passwordEncoder.encode(user.getPrimaryEmail() + user.getPassword());

        User newUser = UserModel.builder()
                .withId(idGenerator.generate())
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .withDateOfBirth(user.getDateOfBirth())
                .withPrimaryEmail(user.getPrimaryEmail())
                .withSecondaryEmail(user.getSecondaryEmail())
                .withPassword(passwordEncoder.encode(password))
                .build();

        return userRepository.create(newUser);
    }
}
