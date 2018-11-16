package com.iparty.model;

/**
 * Created by Maurício Generoso on 11/3/2018
 */
public class ForgetPassword {

    private String token;
    private String email;
    private int code;
    private String password;
    private String confirmPassword;

    public ForgetPassword(String token, String email, int code) {
        this.token = token;
        this.email = email;
        this.code = code;
    }

    public ForgetPassword(String token, String email, int code, String password, String confirmPassword) {
        this.token = token;
        this.email = email;
        this.code = code;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public int getCode() {
        return code;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
