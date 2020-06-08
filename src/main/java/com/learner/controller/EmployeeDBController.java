/**
 * 
 */
package com.learner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.learner.Model.Employee;
import com.learner.dao.EmployeeDAO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author DELL
 *
 */
@RestController
@RequestMapping("/api/v1")
public class EmployeeDBController {

	@Autowired
	private EmployeeDAO employeeDBDao;

	@GetMapping(value = "/employees")
	@ApiOperation(nickname = "EmployeeDetails", value = "All Details about Employees.")

	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeDBDao.getAllEmployee();

		if (employees == null || employees.isEmpty()) {
			return new ResponseEntity("No Data Found!", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(employees, HttpStatus.OK);
	}

	@PostMapping("/addEmployee")
	public ResponseEntity<Void> addEmployee(@RequestBody Employee emp, UriComponentsBuilder ucBuilder) {

		employeeDBDao.addEmployee(emp);
		return new ResponseEntity("Employee added !", HttpStatus.CREATED);

	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Integer id, @RequestBody Employee emp) {

		Employee currentEmp = employeeDBDao.findEmp(id);

		if (currentEmp == null) {
			System.out.println("emp with id " + id + " not found");
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}

		currentEmp.setFirstName(emp.getFirstName());
		currentEmp.setLastName(emp.getLastName());
		currentEmp.setId(emp.getId());
		currentEmp.setEmail(emp.getEmail());

		return new ResponseEntity<Employee>(currentEmp, HttpStatus.OK);
	}

	@GetMapping(value = "/employees")
	@ApiOperation(nickname = "EmployeeDetails", value = "All Details about Employees.")
	@ApiImplicitParams({ @ApiImplicitParam(value = "id", dataType = "Integer") })
	@RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<List<Employee>> deleteEmp(@PathVariable("id") Integer id) {

		List<Employee> emps = employeeDBDao.deleteEmp(id);

		if (emps == null) {
			return new ResponseEntity("Sorry! No data found!", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Employee>>(emps, HttpStatus.OK);
	}

	@PatchMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody String firstName, @PathVariable("id") Integer id) {
		System.out.println("Updating productName " + firstName);

		Employee employee = employeeDBDao.findEmp(id);

		if (employee == null) {
			System.out.println("employee with id " + id + " not found");
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}

		employee.setFirstName(firstName);

		employeeDBDao.updateEmployee(employee);

		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
}
