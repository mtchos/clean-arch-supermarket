package br.com.meli.supermarket.core.domain.entity;

import java.time.LocalDate;

public interface User {

    String getId();

    void setId(String id);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    LocalDate getDateOfBirth();

    void setDateOfBirth(LocalDate dateOfBirth);

    String getPrimaryEmail();

    void setPrimaryEmail(String primaryEmail);

    String getSecondaryEmail();

    void setSecondaryEmail(String secondaryEmail);

    String getPassword();

    void setPassword(String password);

    boolean equals(Object object);

    int hashCode();
}
