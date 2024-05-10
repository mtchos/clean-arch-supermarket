package br.com.archdemo.supermarket.core.domain.entity;

import java.time.LocalDate;

public interface User {

    String getId();

    String getFirstName();

    String getLastName();

    LocalDate getDateOfBirth();

    String getTaxId();

    String getPrimaryEmail();

    String getSecondaryEmail();

    String getPassword();

    boolean equals(Object object);

    int hashCode();
}
