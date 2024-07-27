package com.example.RegLogin.DTO;

import org.springframework.stereotype.Component;

@Component
public class EmployeeDTO {
    private int employeeid;
    private String employeename;
    private String email;
    private String password;
    private String captcha;
    private String HiddenCaptcha;
    private String RealCaptcha;
    private String Otp;
    private String HiddenOtp;

    public EmployeeDTO(int employeeid, String employeename, String email, String password, String captcha, String hiddenCaptcha, String realCaptcha, String otp, String hiddenOtp) {
        this.employeeid = employeeid;
        this.employeename = employeename;
        this.email = email;
        this.password = password;
        this.captcha = captcha;
        HiddenCaptcha = hiddenCaptcha;
        RealCaptcha = realCaptcha;
        Otp = otp;
        HiddenOtp = hiddenOtp;
    }

    public EmployeeDTO() {
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public String getEmployeename() {
        return employeename;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getHiddenCaptcha() {
        return HiddenCaptcha;
    }

    public void setHiddenCaptcha(String hiddenCaptcha) {
        HiddenCaptcha = hiddenCaptcha;
    }

    public String getRealCaptcha() {
        return RealCaptcha;
    }

    public void setRealCaptcha(String realCaptcha) {
        RealCaptcha = realCaptcha;
    }

    public String getOtp() {
        return Otp;
    }

    public String getHiddenOtp() {
        return HiddenOtp;
    }

    public void setHiddenOtp(String hiddenOtp) {
        HiddenOtp = hiddenOtp;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOtp(String otp) {
        Otp = otp;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "employeeid=" + employeeid +
                ", employeename='" + employeename + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
