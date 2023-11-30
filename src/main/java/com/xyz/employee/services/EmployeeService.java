package com.xyz.employee.services;

import com.xyz.employee.bean.AddEditEmployeeRequestBean;
import com.xyz.employee.bean.AddEditEmployeeResponseBean;
import com.xyz.employee.bean.projections.ViewWithBonusProjection;
import com.xyz.employee.exception.CustomException;
import com.xyz.employee.models.Employee;
import com.xyz.employee.repositories.EmployeeRepository;
import com.xyz.employee.repositories.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
	private final EmployeeRepository employeeRepository;
	private final GradeRepository gradeRepository;

	public EmployeeService(EmployeeRepository employeeRepository, GradeRepository gradeRepository) {
		this.employeeRepository = employeeRepository;
		this.gradeRepository = gradeRepository;
	}

	public AddEditEmployeeResponseBean insertEmployee(AddEditEmployeeRequestBean emp)  {
	    // validation
	    String findGrdCode = gradeRepository.checkByGradeCode(emp.getGradeCode());
	    if(!"1".equalsIgnoreCase(findGrdCode)) {
			throw new CustomException("Invalid grade");
	    }
	    Optional<Employee> opt = employeeRepository.findById(emp.getId());
	    if(opt.isPresent()) {
			throw new CustomException("Id " + emp.getId() + " already exist");
	    }
	    // end validation
	    
	    Employee e = new Employee();
	    e.setId(emp.getId());
	    e.setSalary(emp.getSalary());
	    e.setGradeCode(emp.getGradeCode());
	    e.setName(emp.getName());
	    e.setCreatedAt(new Date());
	    
	    employeeRepository.save(e);
	    
	    return new AddEditEmployeeResponseBean("Employee saved");
    }

	public AddEditEmployeeResponseBean editEmployee(AddEditEmployeeRequestBean emp) {
		// validation
	    String findGrdCode = gradeRepository.checkByGradeCode(emp.getGradeCode());
	    if(!"1".equalsIgnoreCase(findGrdCode)) {
			throw new CustomException("Invalid grade");
	    }
	    Optional<Employee> opt = employeeRepository.findById(emp.getId());
	    if(!opt.isPresent()) {
			throw new CustomException("No Employee found with id " + emp.getId());
	    }
	    // end validation
	    
	    Employee e = opt.get();
		e.setSalary(emp.getSalary());
		e.setGradeCode(emp.getGradeCode());
		e.setName(emp.getName());
		e.setUpdatedAt(new Date());
	    
	    employeeRepository.save(e);
	    return new AddEditEmployeeResponseBean("Employee updated");
	}

	public List<ViewWithBonusProjection> viewEmployeeWithBonus() {
		return employeeRepository.viewWithBonus();
	}
}
