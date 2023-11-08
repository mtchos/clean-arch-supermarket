package br.com.meli.supermarket.presenter.controller;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import br.com.meli.supermarket.infrastructure.api.BcryptEncoder;
import br.com.meli.supermarket.infrastructure.model.UserModel;
import br.com.meli.supermarket.presenter.SupermarketApplication;
import br.com.meli.supermarket.infrastructure.repository.UserRepositoryImpl;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SupermarketApplication.class)
@TestPropertySource(
        locations = "classpath:application.properties")
public class UserControllerTest {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private UserController userController;

    public static UserModel testUser = UserModel.builder()
            .withFirstName("Teste")
            .withLastName("da Silva")
            .withDateOfBirth(LocalDate.of(1970, 1, 1))
            .withPrimaryEmail("test@gmail.com")
            .withSecondaryEmail("test@icloud.com")
            .withPassword(new BcryptEncoder().encode("Senha123"))
            .build();

    public static UserModel testUserToo = UserModel.builder()
            .withFirstName("Testa")
            .withLastName("de Sousa")
            .withDateOfBirth(LocalDate.of(2000, 2, 3))
            .withPrimaryEmail("testa@gmail.com")
            .withSecondaryEmail("testa@icloud.com")
            .withPassword(new BcryptEncoder().encode("Abc123"))
            .build();

    @BeforeEach
    void beforeEach() {
        this.userRepository.deleteAll();
    }

    @Test
    public void testCreateUser() {
        UserModel userCreated = userController.create(testUser);
        UserModel userFromDatabase = userController.findById(userCreated.getId());
        Assertions.assertEquals(userFromDatabase, userCreated);
    }

    @Test
    public void testUpdateUser() {
        UserModel userCreated = userController.create(testUser);
        UserModel userFromDatabase = userController.findById(userCreated.getId());
        userFromDatabase.setFirstName("Mudou");
        userFromDatabase.setLastName("o Nome");
        userFromDatabase.setPrimaryEmail("email@bbc.co.uk");
        userController.update(userFromDatabase.getId(), userFromDatabase);
        UserModel userUpdated = userController.findById(userFromDatabase.getId());
        Assertions.assertEquals(userUpdated.getFirstName(), "Mudou");
        Assertions.assertEquals(userUpdated.getLastName(), "o Nome");
        Assertions.assertEquals(userUpdated.getPrimaryEmail(), "email@bbc.co.uk");
    }

    @Test
    public void testFindUserById() {
        UserModel userCreated = userController.create(testUser);
        UserModel userFromDatabase = userController.findById(userCreated.getId());
        Assertions.assertEquals(userCreated, userFromDatabase);
    }

    @Test
    public void testFindAllUsers() {
        UserModel userCreated = userController.create(testUser);
        UserModel userCreatedToo = userController.create(testUserToo);
        List<UserModel> listUsers = userController.findAll();
        Assertions.assertTrue(listUsers.stream().anyMatch(user -> Objects.equals(user.getId(), userCreated.getId())));
        Assertions.assertTrue(listUsers.stream().anyMatch(user -> Objects.equals(user.getId(), userCreatedToo.getId())));
    }
}
