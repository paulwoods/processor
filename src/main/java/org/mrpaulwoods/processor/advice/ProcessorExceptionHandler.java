package org.mrpaulwoods.processor.advice;

import org.mrpaulwoods.processor.exceptions.InvalidInputException;
import org.mrpaulwoods.processor.exceptions.JobNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

@ControllerAdvice
public class ProcessorExceptionHandler {

    @ExceptionHandler(JobNotFoundException.class)
    public ProblemDetail handleException(JobNotFoundException ex) {
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setType(URI.create("http://www.example.com/problems/job/not-found"));
        problem.setTitle("Job - Not Found");
        return problem;
    }

    @ExceptionHandler(InvalidInputException.class)
    public ProblemDetail handleException(InvalidInputException ex) {
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problem.setType(URI.create("http://www.example.com/problems/job/bad-request"));
        problem.setTitle("Job - Invalid Input");
        return problem;
    }

}
