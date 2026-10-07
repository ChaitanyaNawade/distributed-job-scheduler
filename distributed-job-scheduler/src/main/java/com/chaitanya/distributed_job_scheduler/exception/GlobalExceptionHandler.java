package com.chaitanya.distributed_job_scheduler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<String> handleJobNotFoundException(JobNotFoundException e)
    {
       HttpStatus status = HttpStatus.NOT_FOUND;

       return new ResponseEntity<>(e.getMessage(),status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodNotValidException(MethodArgumentNotValidException mobj)
    {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        List<FieldError> errors = mobj.getBindingResult().getFieldErrors();

        Map<String,String> errorsMap = new HashMap<>();

        for(FieldError error : errors)
        {
            String name = error.getField();
            String message = error.getDefaultMessage();

            errorsMap.put(name,message);
        }

        return new ResponseEntity<>(errorsMap,status);
    }
}



