package br.com.meli.supermarket.presenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import br.com.meli.supermarket.infrastructure.controller.UserController;
import br.com.meli.supermarket.infrastructure.model.UserModel;

@RestController
public class UserService {

    private final UserController userController;

    @Autowired
    public UserService(UserController userController) {
        this.userController = userController;
    }

    @GetMapping("/users/{id}")
    public UserModel findById(
            @PathVariable final String id) {
        return userController.findById(id);
    }

    @GetMapping("/users/email")
    public UserModel findByEmail(
            @RequestParam final String email) {
        return userController.findByEmail(email);
    }

    @GetMapping("/users")
    public List<UserModel> findAll() {
        return userController.findAll();
    }

    @GetMapping("/users/name")
    public List<UserModel> findAllByName(
            @RequestParam final String name) {
        return userController.findAll(name);
    }

    @PostMapping("/users")
    public UserModel create(
            @RequestBody final UserModel userModel) {
        return userController.create(userModel);
    }

    @PutMapping("/users")
    public UserModel update(
            @RequestParam
            String id,
            @RequestBody
            UserModel userModel) {
        return userController.update(id, userModel);
    }
}
