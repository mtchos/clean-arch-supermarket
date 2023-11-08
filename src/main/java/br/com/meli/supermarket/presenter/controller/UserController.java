package br.com.meli.supermarket.presenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import br.com.meli.supermarket.infrastructure.service.UserService;
import br.com.meli.supermarket.infrastructure.model.UserModel;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public UserModel findById(
            @PathVariable final String id) {
        return userService.findById(id);
    }

    @GetMapping("/users/email")
    public UserModel findByEmail(
            @RequestParam final String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/users")
    public List<UserModel> findAll() {
        return userService.findAll();
    }

    @GetMapping("/users/name")
    public List<UserModel> findAllByName(
            @RequestParam final String name) {
        return userService.findAll(name);
    }

    @PostMapping("/users")
    public UserModel create(
            @RequestBody final UserModel userModel) {
        return userService.create(userModel);
    }

    @PutMapping("/users")
    public UserModel update(
            @RequestParam
            String id,
            @RequestBody
            UserModel userModel) {
        return userService.update(id, userModel);
    }
}
