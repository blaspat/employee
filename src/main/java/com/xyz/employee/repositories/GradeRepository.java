package com.xyz.employee.repositories;

import com.xyz.employee.models.Grade;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GradeRepository extends CrudRepository<Grade, Long>{
	@Query("SELECT 1 FROM Grade WHERE gradeCode = ?1")
    String checkByGradeCode(String gradeCode);
}
