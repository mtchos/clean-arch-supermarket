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
import java.util.Optional;

import br.com.meli.supermarket.infrastructure.model.UserModel;
import br.com.meli.supermarket.infrastructure.service.UserService;

@RestController("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{accessor}")
    public UserModel findByFilter(
            @PathVariable
            final String accessor,
            @RequestParam(defaultValue = "false")
            final String filter) {
        return switch (filter) {
            case "email" -> this.findByEmail(accessor);
            default -> this.findById(accessor);
        };
    }

    public UserModel findById(
            @PathVariable
            final String id) {
        return userService.findById(id);
    }

    public UserModel findByEmail(
            @RequestParam
            final String email) {
        return userService.findByEmail(email);
    }

    @GetMapping
    public List<UserModel> findAll(
            @RequestParam(required = false)
            final Optional<String> name) {
        if (name.isPresent()) {
            return this.findAllByName(name.get());
        } else {
            return this.findAll();
        }
    }

    public List<UserModel> findAll() {
        return userService.findAll();
    }

    public List<UserModel> findAllByName(
            @RequestParam
            final String name) {
        return userService.findAll(name);
    }

    @PostMapping
    public UserModel create(
            @RequestBody
            final UserModel userModel) {
        return userService.create(userModel);
    }

    @PutMapping
    public UserModel update(
            @RequestParam
            String id,
            @RequestBody
            UserModel userModel) {
        return userService.update(id, userModel);
    }
}
