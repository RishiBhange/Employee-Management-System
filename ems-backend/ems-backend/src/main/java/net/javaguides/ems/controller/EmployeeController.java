package net.javaguides.ems.controller;


import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    //Build Add employee REST API
@PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){

        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);

        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //Build Get Employee By Id REST API
@GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId)
    {
       EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);

       return  ResponseEntity.ok(employeeDto);
    }

    //Build Get All Employee REST API

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees()
    {
     List<EmployeeDto> employees = employeeService.getAllEmployees();

     return ResponseEntity.ok(employees);
    }

    //Build Update Employee REST API

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,@RequestBody EmployeeDto updatedemployee)
    {
       EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updatedemployee);

       return ResponseEntity.ok(employeeDto);
    }

    //Build Delete Employee REST API

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId)
    {
        employeeService.deleteEmployee(employeeId);

        return ResponseEntity.ok("Employee Deleted Successfully!");
    }
}
