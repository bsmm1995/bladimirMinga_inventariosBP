package com.example.inventariobp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class Response<T> {
    private HttpStatus status;
    private String message;
    private Boolean error;
    private T auto;

    public Response() {
        this.status = HttpStatus.OK;
        this.auto = null;
        this.message = HttpStatus.OK.getReasonPhrase();
        this.error = false;
    }
}
