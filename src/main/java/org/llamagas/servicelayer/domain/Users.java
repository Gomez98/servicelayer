package org.llamagas.servicelayer.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Users", schema = "dbo")
@Data
public class Users {
    @Id
    private String id;
    private String username;
    private String password;
    private String name;
}

