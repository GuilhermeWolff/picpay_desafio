package com.picpay.dto.authorization;

public class AuthorizationDTO {

    public AuthorizationDTO(String email, String password){
        this.email = email;
        this.password = password;
    }

    public AuthorizationDTO(){}

    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
