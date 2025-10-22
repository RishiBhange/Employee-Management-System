    package net.javaguides.ems.service.impl;

    import lombok.AllArgsConstructor;
    import net.javaguides.ems.dto.EmployeeDto;
    import net.javaguides.ems.entity.Employee;
    import net.javaguides.ems.exception.ResourceNotFoundException;
    import net.javaguides.ems.mapper.EmployeeMapper;
    import net.javaguides.ems.repository.EmployeeRepository;
    import net.javaguides.ems.service.EmployeeService;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    @AllArgsConstructor
    public class EmployeeServiceImpl implements EmployeeService {

        private EmployeeRepository employeeRepository;

        @Override
        public EmployeeDto createEmployee(EmployeeDto employeeDto) {

            Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
            Employee savedEmployee = employeeRepository.save(employee);
            return EmployeeMapper.mapToEmployeeDTO(savedEmployee);
        }

        @Override
        public EmployeeDto getEmployeeById(Long employeeid) {

            Employee employee = employeeRepository.findById(employeeid)
                    .orElseThrow( () -> new ResourceNotFoundException("Employee does not exist with id:" + employeeid));

            return EmployeeMapper.mapToEmployeeDTO(employee);
        }

        @Override
        public List<EmployeeDto> getAllEmployees() {
            List<Employee> employees = employeeRepository.findAll();

            return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDTO(employee))
                    .collect(Collectors.toList());

        }

        @Override
        public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
           Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow( () -> new ResourceNotFoundException("Employee with given id does not exist:" + employeeId) );

           employee.setFirstName(updatedEmployee.getFirstName());
           employee.setLastName(updatedEmployee.getLastName());
           employee.setEmail(updatedEmployee.getEmail());

           Employee updatedEmployeeobj = employeeRepository.save(employee);

           return  EmployeeMapper.mapToEmployeeDTO(updatedEmployeeobj);
        }

        @Override
        public void deleteEmployee(Long employeeId) {

            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow( () -> new ResourceNotFoundException("Employee with given id does not exist:" + employeeId) );

            employeeRepository.deleteById(employeeId);

        }
    }
