package com.example.ems.services;

import com.example.ems.exceptions.AlreadyExistsException;
import com.example.ems.exceptions.NotFoundException;
import com.example.ems.model.Employee;
import com.example.ems.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    public List<Employee> getEmployees() {
        return repository.findAll();
    }
    @Override
    public Employee addEmployee(Employee employee) {
        if (employeeAlreadyExists(employee.getEmail())){
            throw  new AlreadyExistsException(employee.getEmail()+ " already exists!");
        }
        return repository.save(employee);
    }


    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        return repository.findById(id).map(st -> {
            st.setFirstName(employee.getFirstName());
            st.setLastName(employee.getLastName());
            st.setEmail(employee.getEmail());
            st.setDepartment(employee.getDepartment());
            return repository.save(st);
        }).orElseThrow(() -> new NotFoundException("Sorry, this user could not be found"));
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sorry, no user found with the Id :" +id));
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!repository.existsById(id)){
            throw new NotFoundException("Sorry, user not found");
        }
        repository.deleteById(id);
    }
    private boolean employeeAlreadyExists(String email) {
        return repository.findByEmail(email).isPresent();
    }


}
