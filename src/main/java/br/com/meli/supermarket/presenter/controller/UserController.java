package br.com.archdemo.supermarket.presenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import br.com.archdemo.supermarket.infrastructure.model.UserModel;
import br.com.archdemo.supermarket.infrastructure.service.UserService;

@RestController("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{accessor}")
    public ResponseEntity<UserModel> findByFilter(
            @PathVariable
            final String accessor,
            @RequestParam(defaultValue = "false")
            final String filter) {
        if (Objects.equals(filter, "email")) {
            return this.findByEmail(accessor);
        } else {
            return this.findById(accessor);
        }
    }

    public ResponseEntity<UserModel> findById(
            @PathVariable
            final String id) {
        UserModel userModel = userService.findById(id);
        return ResponseEntity.ok(userModel);
    }

    public ResponseEntity<UserModel> findByEmail(
            @RequestParam
            final String email) {
        UserModel userModel = userService.findByEmail(email);
        return ResponseEntity.ok(userModel);
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> findAll(
            @RequestParam(required = false)
            final Optional<String> name) {
        if (name.isPresent()) {
            return this.findAllByName(name.get());
        } else {
            return this.findAll();
        }
    }

    public ResponseEntity<List<UserModel>> findAll() {
        List<UserModel> userModels = userService.findAll();
        return ResponseEntity.ok(userModels);

    }

    public ResponseEntity<List<UserModel>> findAllByName(
            @RequestParam
            final String name) {
        List<UserModel> userModels = userService.findAll(name);
        return ResponseEntity.ok(userModels);
    }

    @PostMapping
    public ResponseEntity<UserModel> create(
            @RequestBody
            final UserModel userModel) {
        UserModel createdModel = userService.create(userModel);
        return new ResponseEntity<>(createdModel, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserModel> update(
            @RequestParam
            String id,
            @RequestBody
            UserModel userModel) {
        UserModel updatedModel = userService.update(id, userModel);
        return new ResponseEntity<>(updatedModel, HttpStatus.OK);
    }
}
