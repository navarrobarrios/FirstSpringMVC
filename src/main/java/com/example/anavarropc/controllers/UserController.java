package com.example.anavarropc.controllers;

import com.example.anavarropc.beans.ErrorCodesAplication;
import com.example.anavarropc.beans.User;
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
import java.util.List;

@RestController
public class UserController extends ResponseEntityExceptionHandler {

    //region Variables
    @Autowired
    private IUserService userService;
    //endregion
    @RequestMapping("api/user/getUser")
    @ResponseBody
    public ResponseEntity<GeneralResponse> getUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("username_request") String usernameRequest){

        GeneralResponse userResponse = new GeneralResponse();
        User user_by_id = userService.getUserByUsername(usernameRequest);
        if(user_by_id != null){
            userResponse.setSuccess(true);
            userResponse.setResult(user_by_id);
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }else{
            userResponse.setSuccess(false);
            userResponse.setResult(new ErrorResponse(ErrorCodesAplication.ERROR_001));
            return new ResponseEntity<>(userResponse, HttpStatus.OK); }

    }

    @RequestMapping("api/user/getUsers")
    @ResponseBody
    public ResponseEntity<GeneralResponse> getUsers(
            @RequestParam("username") String username,
            @RequestParam("password") String password){

        GeneralResponse userResponse = new GeneralResponse();
        List<User> users = userService.getAllUsers();
        userResponse.setSuccess(true);
        userResponse.setResult(users);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
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
