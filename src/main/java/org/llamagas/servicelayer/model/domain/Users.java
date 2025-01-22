package org.llamagas.servicelayer.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Users {
    @Id
    private String id;
    private String username;
    private String password;
    private String name;
}

