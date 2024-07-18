package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Response.RestResponse;

//Intervention ExceptionHandler (Exception only occur in Controller) in global area ( Display Exception for client) 
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = { InvalidException.class })
    public ResponseEntity<Object> handleIdException(Exception ex) {

        RestResponse<Object> res = new RestResponse<Object>();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setError(ex.getMessage());
        res.setMessage("Request not valid...");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        BindingResult userBindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = userBindingResult.getFieldErrors();

        List<String> errors = fieldErrors.stream().map(f -> f.getDefaultMessage()).collect(Collectors.toList());

        RestResponse<Object> res = new RestResponse<Object>();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setError("Invalid request content");
        res.setMessage(errors.size() > 1 ? errors : errors.get(0));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(value = { BadCredentialsException.class, UsernameNotFoundException.class })
    // Parameter is Exception ex because don't know will run into what exception
    public ResponseEntity<Object> handleMultiException(Exception ex) {

        RestResponse<Object> res = new RestResponse<Object>();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setError(ex.getMessage());
        res.setMessage("Info login not valid...");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }
}
