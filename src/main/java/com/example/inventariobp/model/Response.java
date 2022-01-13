package com.example.inventariobp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
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
