package com.xyz.employee.repositories;

import com.xyz.employee.bean.projections.ViewWithBonusProjection;
import com.xyz.employee.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    Optional<Employee> findEmployeeByName(String name);

    Optional<Employee> findEmployeeByGradeCode(String gradeCode);

    @Query(nativeQuery = true,
            value = "SELECT e.id, e.name, e.salary, CONCAT(g.grade_code, ':', g.description) as gradeCode, ROUND(e.salary + (e.salary * g.bonus_percentage/100),0) as totalBonus " +
                            "FROM employee e join grade g on e.GRADE_CODE = g.GRADE_CODE")
    List<ViewWithBonusProjection> viewWithBonus();
}
