package br.com.meli.supermarket.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.Objects;

import br.com.meli.supermarket.core.domain.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_table")
public class UserModel implements User {

    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth", columnDefinition = "DATE")
    private LocalDate dateOfBirth;

    @Column(name = "primary_email")
    private String primaryEmail;

    @Column(name = "secondary_email")
    private String secondaryEmail;

    @JsonIgnore
    private String password;

    public UserModel() {

    }

    private UserModel(UserBuilder userBuilder) {
        this.id = userBuilder.id;
        this.firstName = userBuilder.firstName;
        this.lastName = userBuilder.lastName;
        this.dateOfBirth = userBuilder.dateOfBirth;
        this.primaryEmail = userBuilder.primaryEmail;
        this.secondaryEmail = userBuilder.secondaryEmail;
        this.password = userBuilder.password;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static UserModel forWeb(final User user) {
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setDateOfBirth(user.getDateOfBirth());
        userModel.setPrimaryEmail(user.getPrimaryEmail());
        userModel.setSecondaryEmail(user.getSecondaryEmail());
        return userModel;
    }

    public static UserModel forCreate(final User user) {
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setDateOfBirth(user.getDateOfBirth());
        userModel.setPrimaryEmail(user.getPrimaryEmail());
        userModel.setSecondaryEmail(user.getSecondaryEmail());
        userModel.setPassword(user.getPassword());
        return userModel;
    }

    public static UserModel forUpdate(final User user) {
        UserModel userModel = new UserModel();
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setDateOfBirth(user.getDateOfBirth());
        userModel.setPrimaryEmail(user.getPrimaryEmail());
        userModel.setSecondaryEmail(user.getSecondaryEmail());
        return userModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserModel userModel)) return false;
        return Objects.equals(getId(), userModel.getId())
                && Objects.equals(getFirstName(), userModel.getFirstName())
                && Objects.equals(getLastName(), userModel.getLastName())
                && Objects.equals(getDateOfBirth(), userModel.getDateOfBirth())
                && Objects.equals(getPrimaryEmail(), userModel.getPrimaryEmail())
                && Objects.equals(getSecondaryEmail(), userModel.getSecondaryEmail())
                && Objects.equals(getPassword(), userModel.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getFirstName(),
                getLastName(),
                getDateOfBirth(),
                getPrimaryEmail(),
                getSecondaryEmail(),
                getPassword());
    }

    public static class UserBuilder {
        private String id;

        private String firstName;

        private String lastName;

        private LocalDate dateOfBirth;

        private String primaryEmail;

        private String secondaryEmail;

        private String password;

        public UserModel build() {
            return new UserModel(this);
        }

        public UserBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public UserBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder withDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public UserBuilder withPrimaryEmail(String primaryEmail) {
            this.primaryEmail = primaryEmail;
            return this;
        }

        public UserBuilder withSecondaryEmail(String secondaryEmail) {
            this.secondaryEmail = secondaryEmail;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }
    }
}
