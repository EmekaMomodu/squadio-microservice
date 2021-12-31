package com.emekamomodu.squadio.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.MethodNotAllowedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/30/21 11:58 PM
 */
@ControllerAdvice
public class ErrorHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    protected ResponseEntity<Error> handleProcessableMsgException(HttpMessageNotReadableException httpMessageNotReadableException,
                                                                  HttpServletResponse response) {
        logger.error(httpMessageNotReadableException.toString());
        return buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, httpMessageNotReadableException.getMessage());
    }

    @ExceptionHandler(value = ObjectNotFoundException.class)
    protected ResponseEntity<Error> handleException(ObjectNotFoundException objectNotFoundException, HttpServletRequest request) {
        logger.error(objectNotFoundException.toString());
        return buildResponse(HttpStatus.NOT_FOUND, objectNotFoundException.getMessage());
    }

    @ExceptionHandler(value = MethodNotAllowedException.class)
    protected ResponseEntity<Error> handleException(MethodNotAllowedException methodNotAllowedException, HttpServletRequest request) {
        logger.error(methodNotAllowedException.toString());
        return buildResponse(HttpStatus.METHOD_NOT_ALLOWED, methodNotAllowedException.getMessage());
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<Error> handleException(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException,
                                                    HttpServletRequest request) {
        logger.error(httpRequestMethodNotSupportedException.toString());
        return buildResponse(HttpStatus.METHOD_NOT_ALLOWED, httpRequestMethodNotSupportedException.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Error> handleException(Exception exception) {
        logger.error(exception.toString());
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    private ResponseEntity<Error> buildResponse(HttpStatus status, String message) {
        return new ResponseEntity<Error>(new Error(status, message), status);
    }

}
