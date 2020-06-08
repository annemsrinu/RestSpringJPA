/**
 * 
 */
package com.learner.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.learner.Model.Employee;

/**
 * @author DELL
 *
 */
@Repository("employeeDBDao")
@Transactional
public class EmployeeDBDAOImpl implements EmployeeDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> employees = entityManager.createQuery("from Employee").getResultList();
		return employees;
	}

	@Override
	public Employee findEmp(Integer id) {

		return null;
	}

	@Override
	public List<Employee> deleteEmp(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addEmployee(Employee emp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateEmployee(Employee emp) {
		// TODO Auto-generated method stub

	}

}
