package br.com.archdemo.supermarket.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import br.com.archdemo.supermarket.infrastructure.model.UserModel;

@Repository
public interface JpaUserRepository extends CrudRepository<UserModel, String> {

    Optional<UserModel> findByPrimaryEmail(String primaryEmail);

    Optional<UserModel> findBySecondaryEmail(String secondaryEmail);

    Iterable<UserModel> findAllByFirstName(String firstName);
}
