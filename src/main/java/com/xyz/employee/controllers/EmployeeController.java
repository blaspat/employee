package com.xyz.employee.controllers;

import com.xyz.employee.bean.AddEditEmployeeRequestBean;
import com.xyz.employee.bean.AddEditEmployeeResponseBean;
import com.xyz.employee.bean.projections.ViewWithBonusProjection;
import com.xyz.employee.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	private final EmployeeService empService;

	public EmployeeController(EmployeeService empService) {
		this.empService = empService;
	}

	@PostMapping("/add")
	public AddEditEmployeeResponseBean addEmp (@RequestBody AddEditEmployeeRequestBean emp) {
		return empService.insertEmployee(emp);
	}
	
	@PutMapping("/edit")
	public AddEditEmployeeResponseBean editEmp (@RequestBody AddEditEmployeeRequestBean emp) {
	    return empService.editEmployee(emp);
	}
	
	@GetMapping("/view")
	public List<ViewWithBonusProjection> viewEmp() {
		return empService.viewEmployeeWithBonus();
	}
}
