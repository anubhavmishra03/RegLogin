package com.example.RegLogin.EmployeeController;

import com.example.RegLogin.DTO.EmployeeDTO;
import com.example.RegLogin.DTO.LoginDTO;
import com.example.RegLogin.Service.EmployeeService;
import com.example.RegLogin.response.LoginResponse;
import com.example.RegLogin.response.RegResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/register")
    public String showRegisterPage(RegResponse regResponse, Model model) {
        EmployeeDTO employeeDTO = employeeService.loadRegister();
        model.addAttribute("employeeDTO", employeeDTO);
        model.addAttribute("regResponse", regResponse);
        return "reg";
    }

    @PostMapping(path = "/register")
    public String saveEmployee(EmployeeDTO employeeDTO, RedirectAttributes redirectAttributes) {
        RegResponse regResponse = employeeService.verifyEmployee(employeeDTO);
        if (regResponse.getError().equals(false)) {
            redirectAttributes.addFlashAttribute("employeeDTO", employeeDTO);
            redirectAttributes.addFlashAttribute("regResponse", regResponse);
            return "redirect:/employee/verify";
        } else {
            redirectAttributes.addFlashAttribute("regResponse", regResponse);
            return "redirect:/employee/register";
        }
    }

    @GetMapping(path = "/verify")
    public String verifyEmployee(EmployeeDTO employeeDTO, RegResponse regResponse,Model model) {
        model.addAttribute("employeeDTO", employeeDTO);
        model.addAttribute("regResponse", regResponse);
        return "verify";
    }

    @PostMapping(path = "/verify")
    public String processVerify(EmployeeDTO employeeDTO, RedirectAttributes redirectAttributes) {
        if (employeeDTO.getOtp().equals(employeeDTO.getHiddenOtp())) {
            employeeService.addEmployee(employeeDTO);
            return "regSuccess";
        }
        redirectAttributes.addFlashAttribute("employeeDTO", employeeDTO);
        redirectAttributes.addFlashAttribute("regResponse", new RegResponse("Invalid OTP", true));
        return "redirect:/employee/verify";
    }

    @GetMapping(path = "/login")
    public String showLoginPage(LoginResponse loginResponse, Model model) {
        LoginDTO loginDTO = employeeService.loadLogin();
        model.addAttribute("loginDTO", loginDTO);
        model.addAttribute("loginResponse", loginResponse);
        return "login";
    }

    @PostMapping(path = "/login")
    public String processLogin(LoginDTO loginDTO, Model model, RedirectAttributes redirectAttributes){
        LoginResponse loginResponse = employeeService.loginEmployee(loginDTO);
        if (loginResponse.getStatus().equals(false)) {
            model.addAttribute("loginResponse", loginResponse);
            model.addAttribute("loginDTO", loginDTO);
            return "loginSuccess";
        } else {
            redirectAttributes.addFlashAttribute("loginResponse", loginResponse);
            return "redirect:/employee/login";
        }
    }
}
