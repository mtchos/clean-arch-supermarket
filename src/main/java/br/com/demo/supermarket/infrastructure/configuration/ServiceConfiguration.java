package br.com.demo.supermarket.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.demo.supermarket.core.usecase.CreateUser;
import br.com.demo.supermarket.core.usecase.FindUser;
import br.com.demo.supermarket.core.usecase.UpdateUser;
import br.com.demo.supermarket.infrastructure.service.UserService;
import br.com.demo.supermarket.presenter.configuration.PresenterConfiguration;

@Configuration
public class ServiceConfiguration {

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
    public UserService userService() {
        return new UserService(createUser(), findUser(), updateUser());
    }
}
