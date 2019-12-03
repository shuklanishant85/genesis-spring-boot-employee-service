package com.genesis.employee.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.genesis.employee.model.Employee;

@Repository
public class EmployeeRepository implements CrudRepository<Employee, Long> {

	private static final Map<Long, Employee> employeeData = new HashMap<Long, Employee>() {
		private static final long serialVersionUID = -3970206781360313502L;
		{
			put(111L, new Employee(111, "nishant"));
			put(222L, new Employee(222, "niwesh"));
		}
	};

	@Override
	public long count() {
		return employeeData.size();
	}

	@Override
	public void delete(Employee employee) {
		if (employeeData.containsKey(employee.getId())) {
			employeeData.remove(employee.getId());
		}
	}

	@Override
	public void deleteAll() {
		for (long id : employeeData.keySet()) {
			employeeData.remove(id);
		}
	}

	@Override
	public void deleteAll(Iterable<? extends Employee> employee) {
		// do nothing
	}

	@Override
	public void deleteById(Long id) {
		employeeData.remove(id);
	}

	@Override
	public boolean existsById(Long id) {
		return employeeData.containsKey(id);
	}

	@Override
	public Iterable<Employee> findAll() {
		return employeeData.values();
	}

	@Override
	public Iterable<Employee> findAllById(Iterable<Long> arg0) {
		return null;
	}

	@Override
	public Optional<Employee> findById(Long arg0) {
		return null;
	}

	public Employee findEmployeeById(Long id) {
		return employeeData.get(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Employee save(Employee employee) {
		if (employeeData.containsKey(employee.getId())) {
			return null;
		}
		employeeData.put(employee.getId(), employee);
		return employee;
	}

	@Override
	public <S extends Employee> Iterable<S> saveAll(Iterable<S> arg0) {
		return null;
	}

}