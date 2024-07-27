package com.example.RegLogin.Service;

import com.example.RegLogin.DTO.EmployeeDTO;
import com.example.RegLogin.DTO.LoginDTO;
import com.example.RegLogin.response.LoginResponse;
import com.example.RegLogin.response.RegResponse;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    void addEmployee(EmployeeDTO employeeDTO);
    LoginResponse loginEmployee(LoginDTO loginDTO);
    RegResponse verifyEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO loadRegister();
    LoginDTO loadLogin();
}
