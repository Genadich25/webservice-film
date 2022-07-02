package com.webservicewithapi.webservicefilm.models;

import org.springframework.http.HttpStatus;

public class Error {

    private HttpStatus error;

    public Error(HttpStatus error) {
        this.error = error;
    }

    public HttpStatus getError() {
        return error;
    }

    public void setError(HttpStatus error) {
        this.error = error;
    }
}
