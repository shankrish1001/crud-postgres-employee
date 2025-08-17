package com.shankrish.crudpostgresemployee.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    private String employeeName;
    private String employeeEmail;
    private String employeeDept;
    private Integer employeeAge;

}
