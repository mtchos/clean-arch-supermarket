package br.com.meli.supermarket.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.Objects;

import br.com.meli.supermarket.core.domain.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_table")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    @Column(name = "tax_id")
    private String taxId;

    @Column(name = "primary_email")
    private String primaryEmail;

    @Column(name = "secondary_email")
    private String secondaryEmail;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private String password;


    public static UserModel forWeb(final User user) {
        return UserModel.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .taxId(user.getTaxId())
                .primaryEmail(user.getPrimaryEmail())
                .secondaryEmail(user.getSecondaryEmail())
                .build();
    }

    public static UserModel forCreate(final User user) {
        return UserModel.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .taxId(user.getTaxId())
                .primaryEmail(user.getPrimaryEmail())
                .secondaryEmail(user.getSecondaryEmail())
                .password(user.getPassword())
                .build();
    }

    public static UserModel forUpdate(final User user) {
        return UserModel.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .taxId(user.getTaxId())
                .primaryEmail(user.getPrimaryEmail())
                .secondaryEmail(user.getSecondaryEmail())
                .build();
    }

       @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserModel userModel)) return false;
        return Objects.equals(getId(), userModel.getId())
                && Objects.equals(getFirstName(), userModel.getFirstName())
                && Objects.equals(getLastName(), userModel.getLastName())
                && Objects.equals(getDateOfBirth(), userModel.getDateOfBirth())
                && Objects.equals(getTaxId(), userModel.getTaxId())
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
                getTaxId(),
                getPrimaryEmail(),
                getSecondaryEmail(),
                getPassword());
    }
}
