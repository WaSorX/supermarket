package com.utrechtfour.supermarket.errors;

import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyNameException;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerExceptionHandler {



    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError exceptionHandler(MethodArgumentNotValidException e){
        List<String> errors = new ArrayList<String>();
        for (FieldError error:e.getBindingResult().getFieldErrors()){
            errors.add(error.getField() +": " + error.getDefaultMessage());
        }
        for (ObjectError error : e.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
            return new ApiError(HttpStatus.BAD_REQUEST,"Fields missing or not Valid", errors);
    }
    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError exceptionHandler(DataIntegrityViolationException e){
        List<String> errors = new ArrayList<String>();
        errors.add(e.getRootCause().getMessage());
        return new ApiError(HttpStatus.BAD_REQUEST,"Data Integrity Violation",errors);
    }

    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError exceptionHandler(HttpMessageNotReadableException e){
        List<String> errors = new ArrayList<String>();
        errors.add(e.getMostSpecificCause().getMessage());
        return new ApiError(HttpStatus.BAD_REQUEST,"Property not readable",errors);
    }

    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError exceptionHandler(ValidationException e){
        List<String> errors = new ArrayList<>();
        errors.add(e.getLocalizedMessage());
        return new ApiError(HttpStatus.BAD_REQUEST,"Validation exception",errors);
    }

    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError exceptionHandler(NoSuchElementException e){
        List<String> errors = new ArrayList<>();
        System.out.println(e.getMessage());
        errors.add(e.getMessage());
        return new ApiError(HttpStatus.BAD_REQUEST,"No such Element Exception",errors);
    }

    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError exceptionHandler(InvalidDataAccessApiUsageException e){
        List<String> errors = new ArrayList<>();
        errors.add(e.getLocalizedMessage());
        return new ApiError(HttpStatus.BAD_REQUEST,"Invalid Data Access Api Usage Exception", errors);
    }

    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError exceptionHandler(InvalidConfigurationPropertyValueException e){
        List<String> errors = new ArrayList<>();
        errors.add(e.getMessage());
        return new ApiError(HttpStatus.BAD_REQUEST,"Invalid property value",errors);
    }

    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError exceptionHandler(InvalidConfigurationPropertyNameException e){
        List<String> errors = new ArrayList<>();
        errors.add(e.getMessage());
        return new ApiError(HttpStatus.BAD_REQUEST,"Invalid property or property not yet supported",errors);
    }

    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError exceptionHandler(TransactionSystemException e){
        List<String> errors = new ArrayList<>();
        errors.add(e.getMessage());
        errors.add(e.getCause().getLocalizedMessage());

        return new ApiError(HttpStatus.BAD_REQUEST,"Invalid property values",errors);
    }


}
