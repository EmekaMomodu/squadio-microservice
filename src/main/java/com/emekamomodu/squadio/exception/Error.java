package com.emekamomodu.squadio.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/31/21 12:02 AM
 */
public class Error {

    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;

    public Error(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
