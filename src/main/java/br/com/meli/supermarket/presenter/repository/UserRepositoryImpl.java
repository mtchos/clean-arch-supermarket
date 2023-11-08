package br.com.meli.supermarket.presenter.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import br.com.meli.supermarket.core.domain.entity.User;
import br.com.meli.supermarket.core.usecase.contract.UserRepository;
import br.com.meli.supermarket.infrastructure.model.UserModel;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository repository;

    @Autowired
    public UserRepositoryImpl(JpaUserRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserModel create(User user) {
        UserModel userModel = UserModel.forCreate(user);
        return repository.save(userModel);
    }

    @Override
    public UserModel findById(String id) {
        Optional<UserModel> user = repository.findById(id);
        return user.orElseThrow();
    }

    @Override
    public UserModel findByEmail(String email) {
        Optional<UserModel> userFromPrimaryEmail = repository.findByPrimaryEmail(email);
        Optional<UserModel> userFromSecondaryEmail = repository.findBySecondaryEmail(email);
        return userFromPrimaryEmail.orElseGet(userFromSecondaryEmail::orElseThrow);
    }

    @Override
    public List<? extends User> findAll() {
        Iterable<UserModel> users = this.repository.findAll();
        return StreamSupport.stream(users.spliterator(), false).toList();
    }

    @Override
    public List<? extends User> findAll(String name) {
        Iterable<UserModel> users = this.repository.findAllByFirstName(name);
        return StreamSupport.stream(users.spliterator(), false).toList();
    }

    @Override
    public UserModel update(String id, User user) {
        UserModel userModel = UserModel.forUpdate(user);
        boolean userExist = this.repository.existsById(id);
        if (userExist) {
            userModel.setId(id);
            this.repository.save(userModel);
        }
        return (UserModel) user;
    }

    public void deleteAll() {
        this.repository.deleteAll();
    }
}
