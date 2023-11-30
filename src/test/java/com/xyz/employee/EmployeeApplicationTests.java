package com.xyz.employee;


import com.xyz.employee.bean.AddEditEmployeeRequestBean;
import com.xyz.employee.bean.AddEditEmployeeResponseBean;
import com.xyz.employee.bean.projections.ViewWithBonusProjection;
import com.xyz.employee.exception.CustomException;
import com.xyz.employee.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeApplicationTests {
	@Autowired
	EmployeeService employeeService;

	final AddEditEmployeeResponseBean successAddResponse = new AddEditEmployeeResponseBean("Employee saved");
	final AddEditEmployeeResponseBean successEditResponse = new AddEditEmployeeResponseBean("Employee updated");
	final AddEditEmployeeResponseBean failedGradeEditResponse = new AddEditEmployeeResponseBean("Invalid grade");

	@Test
	public void contextLoads() {
	}
	
	/* Add */
	@Test
	@Transactional
	@Rollback
	void testAddEmployeeSuccess() {
		AddEditEmployeeRequestBean empReq1 = new AddEditEmployeeRequestBean();
		empReq1.setId(8l);
		empReq1.setName("Test1");
		empReq1.setGradeCode("1");
		empReq1.setSalary(10000000d);
		AddEditEmployeeResponseBean addEditEmployeeResponseBean = employeeService.insertEmployee(empReq1);
		assertEquals(successAddResponse.getMessage(), addEditEmployeeResponseBean.getMessage());
	}
	
	@Test
	void testAddEmployeeFailedGrade() {
		AddEditEmployeeRequestBean empReq1 = new AddEditEmployeeRequestBean();
		empReq1.setId(1l);
		empReq1.setName("Test");
		empReq1.setGradeCode("0");
		empReq1.setSalary(10000000d);
		CustomException customException = assertThrows(CustomException.class, () -> employeeService.insertEmployee(empReq1));
		assertEquals(failedGradeEditResponse.getMessage(), customException.getMessage());
	}
	
	@Test
	void testAddEmployeeFailedAlreadyExists() {
		AddEditEmployeeRequestBean empReq1 = new AddEditEmployeeRequestBean();
		empReq1.setId(1l);
		empReq1.setName("Test");
		empReq1.setGradeCode("1");
		empReq1.setSalary(10000000d);
		CustomException customException = assertThrows(CustomException.class, () -> employeeService.insertEmployee(empReq1));
		assertTrue(customException.getMessage().endsWith("already exist"));
	}
	
	/* Edit */
	@Test
	@Transactional
	@Rollback
	void testEditEmployeeSuccess() {
		AddEditEmployeeRequestBean empReq1 = new AddEditEmployeeRequestBean();
		empReq1.setId(1l);
		empReq1.setName("Test1");
		empReq1.setGradeCode("1");
		empReq1.setSalary(10000000d);
		AddEditEmployeeResponseBean addEditEmployeeResponseBean = employeeService.editEmployee(empReq1);
		assertEquals(successEditResponse.getMessage(), addEditEmployeeResponseBean.getMessage());
	}

	@Test
	void testEditEmployeeFailedGrade() {
		AddEditEmployeeRequestBean empReq1 = new AddEditEmployeeRequestBean();
		empReq1.setId(1l);
		empReq1.setName("Test");
		empReq1.setGradeCode("0");
		empReq1.setSalary(10000000d);
		CustomException customException = assertThrows(CustomException.class, () -> employeeService.editEmployee(empReq1));
		assertEquals(failedGradeEditResponse.getMessage(), customException.getMessage());
	}
	
	@Test
	void testEditEmployeeFailedNotFound() {
		AddEditEmployeeRequestBean empReq1 = new AddEditEmployeeRequestBean();
		empReq1.setId(1124125192510l);
		empReq1.setName("Test");
		empReq1.setGradeCode("2");
		empReq1.setSalary(10000000d);
		CustomException customException = assertThrows(CustomException.class, () -> employeeService.editEmployee(empReq1));
		assertTrue(customException.getMessage().startsWith("No Employee found"));
	}
	
	/* View Bonus */
	@Test
	void testViewBonus() {
		List<ViewWithBonusProjection> result = employeeService.viewEmployeeWithBonus();
		assertNotNull(result);
	}
}
