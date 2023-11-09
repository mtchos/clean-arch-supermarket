package br.com.meli.supermarket.core.usecase.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import br.com.meli.supermarket.core.domain.entity.User;
import br.com.meli.supermarket.core.usecase.contract.UserRepository;
import br.com.meli.supermarket.core.usecase.exception.UserValidationException;

public final class UserValidator {


    public static void validateUserForCreate(User user) {

        if (user == null) throw new UserValidationException("User should not be null");

        if (user.getFirstName().length() == 0) throw new UserValidationException("First name is required");

        if (user.getPrimaryEmail().length() == 0) throw new UserValidationException("Primary e-mail is required");

        if (user.getTaxId().length() == 0) throw new UserValidationException("Tax id required");
    }

    public static void validateUserEmail(User user, UserRepository userRepository) {

        String userEmail = user.getPrimaryEmail();

        Pattern emailPattern = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        boolean emailMatches = emailPattern.matcher(userEmail).matches();
        if (!emailMatches) throw new UserValidationException("E-mail is not valid");

        User userByEmail = null;
        try {
            userByEmail = userRepository.findByEmail(userEmail);
        } catch (Exception ignored) {
            // Ignored.
        }
        if (userByEmail != null) throw new UserValidationException("E-mail is already being used");
    }

    public static void validateUserTaxId(User user) {

        String originalTaxId = user.getTaxId();

        String unverifiedTaxId = originalTaxId.substring(0, user.getTaxId().length() - 2);

        UserValidationException exception = new UserValidationException("Tax id is not valid");

        List<Integer> taxIdIntegers;
        try {
            taxIdIntegers = unverifiedTaxId.chars().mapToObj(Character::getNumericValue).collect(Collectors.toList());
        } catch (Exception ignored) {
            throw exception;
        }

        Integer firstDigit = UserValidator.getTaxIdDigits(taxIdIntegers, 0);
        taxIdIntegers.add(firstDigit);
        Integer secondDigit = UserValidator.getTaxIdDigits(taxIdIntegers, 1);
        taxIdIntegers.add(secondDigit);
        String verifiedTaxId = taxIdIntegers.stream().map(String::valueOf).collect(Collectors.joining(""));

        if (!originalTaxId.equals(verifiedTaxId)) {
            throw exception;
        }
    }

    private static Integer getTaxIdDigits(List<Integer> initialDigits, int offset) {
        final List<Integer> SEQUENCE = List.of(10, 9, 8, 7, 6, 5, 4, 3, 2);
        int sum = 0;
        for (int i = 0; i < SEQUENCE.size(); i++) {
            sum += SEQUENCE.get(i) * initialDigits.get(i + offset);
        }

        int digit;
        int remainder = sum % 11;
        if (List.of(0, 1).contains(remainder)) {
            digit = 0;
        } else {
            digit = 11 - remainder;
        }
        return digit;
    }
}
