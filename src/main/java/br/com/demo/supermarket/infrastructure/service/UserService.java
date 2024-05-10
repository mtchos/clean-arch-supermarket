package br.com.demo.supermarket.infrastructure.service;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

import br.com.demo.supermarket.core.domain.entity.User;
import br.com.demo.supermarket.core.usecase.CreateUser;
import br.com.demo.supermarket.core.usecase.FindUser;
import br.com.demo.supermarket.core.usecase.UpdateUser;
import br.com.demo.supermarket.infrastructure.exception.HttpException;
import br.com.demo.supermarket.infrastructure.model.UserModel;


public class UserService {

    private final CreateUser createUser;

    private final FindUser findUser;

    private final UpdateUser updateUser;

    public UserService(
            final CreateUser createUser,
            final FindUser findUser,
            final UpdateUser updateUser) {
        this.createUser = createUser;
        this.findUser = findUser;
        this.updateUser = updateUser;
    }

    public UserModel create(final UserModel userModel) {
        User createdUser;
        try {
            createdUser = createUser.create(userModel);
        } catch (Exception ignored) {
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return UserModel.forWeb(createdUser);
    }

    public UserModel findById(final String id) {
        User user;
        try {
            user = findUser.findById(id);
        } catch (Exception ignored) {
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        findUser.findById(id);
        return UserModel.forWeb(user);
    }

    public UserModel findByEmail(final String email) {
        User user;
        try {
            user = findUser.findByEmail(email);
        } catch (Exception ignored) {
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return UserModel.forWeb(user);
    }

    public List<UserModel> findAll() {
        List<? extends User> users;
        try {
            users = findUser.findAll();
        } catch (Exception ignored) {
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return users.stream().map(UserModel::forWeb).collect(Collectors.toList());
    }

    public List<UserModel> findAll(final String name) {
        List<? extends User> users;
        try {
            users = findUser.findAll(name);
        } catch (Exception ignored) {
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return users.stream().map(UserModel::forWeb).collect(Collectors.toList());
    }

    public UserModel update(final String id, final UserModel userModel) {
        User updatedUser;
        try {
            updatedUser = updateUser.update(id, userModel);
        } catch (Exception ignored) {
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return UserModel.forWeb(updatedUser);
    }
}
