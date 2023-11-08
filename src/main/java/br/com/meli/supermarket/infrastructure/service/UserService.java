package br.com.meli.supermarket.infrastructure.service;

import java.util.List;
import java.util.stream.Collectors;

import br.com.meli.supermarket.core.domain.entity.User;
import br.com.meli.supermarket.core.usecase.CreateUser;
import br.com.meli.supermarket.core.usecase.FindUser;
import br.com.meli.supermarket.core.usecase.UpdateUser;
import br.com.meli.supermarket.infrastructure.model.UserModel;


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
        User createdUser = createUser.create(userModel);
        return UserModel.forWeb(createdUser);
    }

    public UserModel findById(final String id) {
        User user = findUser.findById(id);
        return UserModel.forWeb(user);
    }

    public UserModel findByEmail(final String email) {
        User user = findUser.findByEmail(email);
        return UserModel.forWeb(user);
    }

    public List<UserModel> findAll() {
        List<? extends User> users = findUser.findAll();
        return users.stream().map(UserModel::forWeb).collect(Collectors.toList());
    }

    public List<UserModel> findAll(final String name) {
        List<? extends User> users = findUser.findAll(name);
        return users.stream().map(UserModel::forWeb).collect(Collectors.toList());
    }

    public UserModel update(final String id, final UserModel userModel) {
        User updatedUserEntity = updateUser.update(id, userModel);
        return UserModel.forWeb(updatedUserEntity);
    }
}
