package br.com.demo.supermarket.core.usecase.contract;

import java.util.List;

import br.com.demo.supermarket.core.domain.entity.User;

public interface UserRepository {

    User create(User user);

    User findById(String id);

    User findByEmail(String email);

    List<? extends User> findAll();

    List<? extends User> findAll(String name);

    User update(String id, User user);
}
