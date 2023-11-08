package br.com.meli.supermarket.core.usecase.validation;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import br.com.meli.supermarket.core.domain.entity.User;
import br.com.meli.supermarket.core.usecase.exception.UserValidationException;

public final class UserValidator {


    public static void validateCreateUser(User user) {
        if (ObjectUtils.isEmpty(user)) {
            throw new UserValidationException("User should not be null when creating an user");
        }

        if (!StringUtils.hasText(user.getFirstName())) {
            throw new UserValidationException("First name is required when creating an user");
        }

        if (!StringUtils.hasText(user.getPrimaryEmail())) {
            throw new UserValidationException("Primary e-mail is required when creating an user");
        }
    }
}
