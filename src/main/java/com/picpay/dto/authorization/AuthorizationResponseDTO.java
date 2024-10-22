package com.picpay.dto.authorization;

public class AuthorizationResponseDTO {

    private String status;

    private DataDTO data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }
}
