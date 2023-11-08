package br.com.meli.supermarket.core.domain.entity;

import java.time.LocalDate;

public interface User {

    String getId();

    String getFirstName();

    String getLastName();

    LocalDate getDateOfBirth();

    String getPrimaryEmail();

    String getSecondaryEmail();

    String getPassword();

    boolean equals(Object object);

    int hashCode();
}
