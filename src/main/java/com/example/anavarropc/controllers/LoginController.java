package com.example.anavarropc.controllers;

import com.example.anavarropc.beans.*;
import com.example.anavarropc.interfaces.IUserService;
import com.example.anavarropc.responses_entities.ErrorResponse;
import com.example.anavarropc.responses_entities.GeneralResponse;
import com.example.anavarropc.responses_entities.LoginEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.PersistenceException;

@RestController
public class LoginController extends ResponseEntityExceptionHandler {

    //region Variables
    @Autowired
    private IUserService userService;
    //endregion

    @RequestMapping("api/login/requestLogin")
    @ResponseBody
    public ResponseEntity<GeneralResponse> getUser(@RequestParam("username") String username, @RequestParam("password") String password){
        GeneralResponse userResponse = new GeneralResponse();
        User user_by_id = new User(1, "navarrobarrios", "password", "Jose Alberto", "Navarro Barrios", 22);//userService.getUserByUsername(username);
        if(user_by_id != null){
            if(user_by_id.getPassword().equals(password)){
                userResponse.setSuccess(true);
                userResponse.setResult(new LoginEntity(user_by_id.getName(), user_by_id.getLastname()));
                return new ResponseEntity<>(userResponse, HttpStatus.OK);
            }else{
                userResponse.setSuccess(false);
                userResponse.setResult(new ErrorResponse(ErrorCodesAplication.ERROR_002));
                return new ResponseEntity<>(userResponse, HttpStatus.OK);
            }
        }else{
            userResponse.setSuccess(false);
            userResponse.setResult(new ErrorResponse(ErrorCodesAplication.ERROR_001));
            return new ResponseEntity<>(userResponse, HttpStatus.OK); }
    }

    //region Error Handler
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        GeneralResponse userResponse = new GeneralResponse();
        userResponse.setSuccess(false);
        userResponse.setResult(new ErrorResponse(ErrorCodesAplication.ERROR_001));
        return new ResponseEntity<>((Object) userResponse, HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        GeneralResponse userResponse = new GeneralResponse();
        userResponse.setSuccess(false);
        userResponse.setResult(new ErrorResponse(ErrorCodesAplication.ERROR_003));
        return new ResponseEntity<>((Object)userResponse, HttpStatus.OK);
    }

    @ExceptionHandler(value = { PersistenceException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> unknownException() {
        GeneralResponse userResponse = new GeneralResponse();
        userResponse.setSuccess(false);
        userResponse.setResult(new ErrorResponse(ErrorCodesAplication.ERROR_004));
        return new ResponseEntity<>((Object)userResponse, HttpStatus.OK);
    }
    //endregion
}
