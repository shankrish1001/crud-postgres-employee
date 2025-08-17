package com.shankrish.crudpostgresemployee.repository;

import com.shankrish.crudpostgresemployee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM get_employee_by_email(:email)", nativeQuery = true)
    List<Employee> getEmployeeByEmail(@Param("email") String email);

}
