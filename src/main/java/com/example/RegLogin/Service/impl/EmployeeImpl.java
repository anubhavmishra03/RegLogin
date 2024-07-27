package com.example.RegLogin.Service.impl;

import cn.apiclub.captcha.Captcha;
import com.example.RegLogin.Captcha.CaptchaUtil;
import com.example.RegLogin.DTO.EmployeeDTO;
import com.example.RegLogin.DTO.LoginDTO;
import com.example.RegLogin.Entity.Employee;
import com.example.RegLogin.Repo.EmployeeRepo;
import com.example.RegLogin.Service.EmployeeService;
import com.example.RegLogin.response.LoginResponse;
import com.example.RegLogin.response.RegResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class EmployeeImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Override
    public EmployeeDTO loadRegister() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        getCaptcha(employeeDTO);
        String Otp = generateOTP();
        employeeDTO.setHiddenOtp(Otp);
        return employeeDTO;
    }

    @Override
    public RegResponse verifyEmployee(EmployeeDTO employeeDTO) {
        if (employeeDTO.getCaptcha().equals(employeeDTO.getHiddenCaptcha())) {
            Employee employee = employeeRepo.findByEmail(employeeDTO.getEmail());
            if (employee != null) {
                return new RegResponse("Email Already Exists", true);
            } else {
                sendVerificationEmail(employeeDTO.getEmail(), employeeDTO.getHiddenOtp());
                return new RegResponse("Success", false);
            }
        } else {
            return new RegResponse("Incorrect Captcha", true);
        }
    }

    @Override
    public void addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(
                employeeDTO.getEmployeeid(),
                employeeDTO.getEmployeename(),
                employeeDTO.getEmail(),
                this.passwordEncoder.encode(employeeDTO.getPassword())
        );
        employeeRepo.save(employee);
    }

    @Override
    public LoginDTO loadLogin() {
        LoginDTO loginDTO = new LoginDTO();
        getLoginCaptcha(loginDTO);
        return loginDTO;
    }

    @Override
    public LoginResponse loginEmployee(LoginDTO loginDTO) {
        if (loginDTO.getCaptcha().equals(loginDTO.getHiddenCaptcha())) {
            Employee employee1 = employeeRepo.findByEmail(loginDTO.getEmail());
            if(employee1 != null) {
                if (passwordEncoder.matches(loginDTO.getPassword(), employee1.getPassword())) {
                    return new LoginResponse(false, "Login Success");
                } else {
                    return new LoginResponse(true, "Username or Password Incorrect");
                }
            }else {
                return new LoginResponse(true, "Email does not exist");
            }
        } else {
            return new LoginResponse(true, "Incorrect Captcha");
        }
    }

    private void getCaptcha(EmployeeDTO employeeDTO) {
        Captcha captcha = CaptchaUtil.createCaptcha(240, 70);
        employeeDTO.setHiddenCaptcha(captcha.getAnswer());
        employeeDTO.setCaptcha(""); // value entered by the User
        employeeDTO.setRealCaptcha(CaptchaUtil.encodeCaptcha(captcha));
    }

    private String generateOTP(){
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000);
        return String.valueOf(otpValue);
    }

    private void sendVerificationEmail(String email,String otp){
        String subject = "Email verification";
        String body ="your verification otp is: "+otp;
        System.out.println(otp);
        emailService.sendEmail(email,subject,body);
    }

    private void getLoginCaptcha(LoginDTO loginDTO) {
        Captcha captcha = CaptchaUtil.createCaptcha(240, 70);
        loginDTO.setHiddenCaptcha(captcha.getAnswer());
        loginDTO.setCaptcha(""); // value entered by the User
        loginDTO.setRealCaptcha(CaptchaUtil.encodeCaptcha(captcha));
    }
}
