package com.shankrish.crudpostgresemployee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "t_user")
@Builder
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Email
    private String email;
    private String pass;

}
