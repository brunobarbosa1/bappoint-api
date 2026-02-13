package com.wesleysilva.bappoint.infra;

import com.wesleysilva.bappoint.exceptions.CompanyDeleteException;
import com.wesleysilva.bappoint.exceptions.CompanyNotFoundException;
import com.wesleysilva.bappoint.exceptions.EmailAlreadyExistsException;
import com.wesleysilva.bappoint.exceptions.RestErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    private ResponseEntity<RestErrorMessage> emailAlreadyExistsResponse(EmailAlreadyExistsException exception) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.CONFLICT, "Email already exists.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatResponse);
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    private ResponseEntity<RestErrorMessage> companyNotFoundResponse(CompanyNotFoundException exception) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, "Company not found.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(CompanyDeleteException.class)
    private ResponseEntity<RestErrorMessage> companyDeleteResponse(CompanyDeleteException exception) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.CONFLICT, "Failed to delete company.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatResponse);
    }
}
