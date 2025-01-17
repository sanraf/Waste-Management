package com.enviro.assessment.grad001.santosrafaelo.handler;

import com.enviro.assessment.grad001.santosrafaelo.appexception.BusinessOperationFailedException;
import com.enviro.assessment.grad001.santosrafaelo.appexception.WasteEntityNotFoundException;
import com.enviro.assessment.grad001.santosrafaelo.appresponse.APIResponse;
import com.enviro.assessment.grad001.santosrafaelo.dto.ErrorDto;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(WasteEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIResponse<?> wasteEntityNotFoundException(WasteEntityNotFoundException e,HttpServletRequest request){
        APIResponse<?> exceptionResponse = new APIResponse<>();
        return APIResponse.builder()
                .message("Error")
                .statusMessage(e.getMessage())
                .statusCode(HttpStatus.NOT_FOUND)
                .method(request.getMethod())
                .urlInstance(request.getServletPath())
                .build();
    }

    @ExceptionHandler(BusinessOperationFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIResponse<?> businessOperationFailedException(BusinessOperationFailedException e,HttpServletRequest request){
        APIResponse<?> exceptionResponse = new APIResponse<>();
        return APIResponse.builder()
                .message("Error")
                .statusMessage(e.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST)
                .method(request.getMethod())
                .urlInstance(request.getServletPath())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIResponse<?> fieldValidationException(MethodArgumentNotValidException e, HttpServletRequest request){
        List<ErrorDto> errors = new ArrayList<>();
        e.getBindingResult().getFieldErrors()
                .forEach( error -> {
                    ErrorDto errorDto = new ErrorDto(error.getField(), error.getDefaultMessage());
                    errors.add(errorDto);
                });

        return APIResponse.builder()
                .message("Error")
                .statusCode(HttpStatus.BAD_REQUEST)
                .method(request.getMethod())
                .urlInstance(request.getServletPath())
                .errors(errors)
                .build();
    }


}
