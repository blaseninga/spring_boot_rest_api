package com.example.springboot_rest.dao;


import com.example.springboot_rest.entity.Employee;
import jakarta.persistence.EntityManager;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeDAOImpl implements EmployeeDAO{
    @Autowired
    private EntityManager entityManager;
    @Override
    public List<Employee> getAllEmployees() {
        Query query = (Query) entityManager.createQuery("from Employee");
        List<Employee> allEmployees = query.getResultList();
        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {
      Employee newEmployee = entityManager.merge(employee);
      employee.setId(newEmployee.getId());
    }

    @Override
    public Employee getEmployee(int id) {
      Employee employee = entityManager.find(Employee.class, id);
      return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Query<Employee> query = (Query<Employee>) entityManager.createQuery("delete from Employee " + "where id =:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
