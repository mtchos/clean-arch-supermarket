package br.com.meli.supermarket.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.meli.supermarket.core.usecase.CreateUser;
import br.com.meli.supermarket.core.usecase.FindUser;
import br.com.meli.supermarket.core.usecase.UpdateUser;
import br.com.meli.supermarket.infrastructure.service.UserService;
import br.com.meli.supermarket.presenter.configuration.PresenterConfiguration;

@Configuration
public class ControllerConfiguration {

    @Autowired
    private PresenterConfiguration presenterConfiguration;

    @Bean
    public CreateUser createUser() {
        return presenterConfiguration.createUser();
    }

    @Bean
    public FindUser findUser() {
        return presenterConfiguration.findUser();
    }

    @Bean
    public UpdateUser updateUser() {
        return presenterConfiguration.updateUser();
    }

    @Bean
    public UserService userController() {
        return new UserService(createUser(), findUser(), updateUser());
    }
}
