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
            .firstName("Teste")
            .lastName("da Silva")
            .dateOfBirth(LocalDate.of(1970, 1, 1))
            .taxId("95494054861")
            .primaryEmail("test@gmail.com")
            .secondaryEmail("test@icloud.com")
            .password(new BcryptEncoder().encode("Senha123"))
            .build();

    public static UserModel testUserToo = UserModel.builder()
            .firstName("Testa")
            .lastName("de Sousa")
            .dateOfBirth(LocalDate.of(2000, 2, 3))
            .taxId("21168998859")
            .primaryEmail("testa@gmail.com")
            .secondaryEmail("testa@icloud.com")
            .password(new BcryptEncoder().encode("Abc123"))
            .build();

    @BeforeEach
    void beforeEach() {
        this.userRepository.deleteAll();
    }

    @Test
    public void testCreateUser() {
        UserModel userCreated = userController.create(testUser).getBody();
        UserModel userFromDatabase = userController.findById(userCreated.getId()).getBody();
        Assertions.assertEquals(userFromDatabase, userCreated);
    }

    @Test
    public void testUpdateUser() {
        UserModel userCreated = userController.create(testUser).getBody();
        UserModel userFromDatabase = userController.findById(userCreated.getId()).getBody();
        userFromDatabase.setFirstName("Mudou");
        userFromDatabase.setLastName("o Nome");
        userFromDatabase.setPrimaryEmail("email@bbc.co.uk");
        userController.update(userFromDatabase.getId(), userFromDatabase);
        UserModel userUpdated = userController.findById(userFromDatabase.getId()).getBody();
        Assertions.assertEquals(userUpdated.getFirstName(), "Mudou");
        Assertions.assertEquals(userUpdated.getLastName(), "o Nome");
        Assertions.assertEquals(userUpdated.getPrimaryEmail(), "email@bbc.co.uk");
    }

    @Test
    public void testFindUserById() {
        UserModel userCreated = userController.create(testUser).getBody();
        UserModel userFromDatabase = userController.findById(userCreated.getId()).getBody();
        Assertions.assertEquals(userCreated, userFromDatabase);
    }

    @Test
    public void testFindAllUsers() {
        UserModel userCreated = userController.create(testUser).getBody();
        UserModel userCreatedToo = userController.create(testUserToo).getBody();
        List<UserModel> listUsers = userController.findAll().getBody();
        Assertions.assertTrue(listUsers.stream().anyMatch(user -> Objects.equals(user.getId(), userCreated.getId())));
        Assertions.assertTrue(listUsers.stream().anyMatch(user -> Objects.equals(user.getId(), userCreatedToo.getId())));
    }
}
