package com.example.RegLogin.DTO;

public class LoginDTO{
    private String email;
    private String password;
    private String captcha;
    private String HiddenCaptcha;
    private String RealCaptcha;

    public LoginDTO() {
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

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
