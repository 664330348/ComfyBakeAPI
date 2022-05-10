package com.revature.comfybake.User;

import com.revature.comfybake.Principal;
import com.revature.comfybake.Token.TokenService;
import com.revature.comfybake.User.dtos.LoginRequest;
import com.revature.comfybake.User.dtos.NewUserRequest;
import com.revature.comfybake.User.dtos.ProfileResponse;
import com.revature.comfybake.User.dtos.UpdateProfileRequest;
import com.revature.comfybake.exceptions.AuthenticationException;
import com.revature.comfybake.exceptions.ForbiddenException;
import com.revature.comfybake.exceptions.InvalidRequestException;
import com.revature.comfybake.exceptions.ResourceConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private TokenService tokenService;

    @Autowired
    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    //Register a new user as a customer
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value ="registration", produces = "application/json", consumes = "application/json")
    public HashMap<String, Object> register(@RequestBody NewUserRequest newUserRequest) {
        HashMap<String, Object> userList = new HashMap<String, Object>();

        User newUser = userService.register(newUserRequest);
       // UserResponse userResponse = new UserResponse(newUser);

        userList.put("endpoint", " /register");
        userList.put("status", "UP");
        //userList.put("providedValues", userResponse);

        return userList;
    }

    //Login
    @CrossOrigin(exposedHeaders = "authorization")
    @PostMapping(value="login", produces = "application/json", consumes = "application/json")
    public Principal login(@RequestBody LoginRequest request, HttpServletResponse response) {
        Principal principal = new Principal(userService.login(request));

        String token = tokenService.generateToken(principal);
        response.setHeader("authorization", token);
        response.setStatus(201);

        return principal;
    }

    //View User Profile
    @CrossOrigin(exposedHeaders = "authorization")
    @GetMapping(value = "profile",produces = "application/json")
    public HashMap<String, Object> viewProfile(HttpServletRequest request){
        Principal requester = tokenService.extractRequesterDetails(request.getHeader("Authorization"));
        HashMap<String, Object> response = new HashMap<String, Object>();
        ProfileResponse profileResponse = userService.viewUserProfile(requester.getUserId());
        response.put("profileResponse: ", profileResponse);
        return response;
    }

    //Update User Profile
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(exposedHeaders = "authorization")
    @PutMapping(value = "profile",produces = "application/json")
    public HashMap<String, Object> updateProfile(@RequestBody UpdateProfileRequest updateProfileRequest, HttpServletRequest request){
        Principal requester = tokenService.extractRequesterDetails(request.getHeader("Authorization"));
        HashMap<String, Object> response = new HashMap<String, Object>();
        userService.updateUserProfile(requester.getUserId(), updateProfileRequest);
        response.put("status: ", "update");
        return response;
    }

    //View User Wallet
    @CrossOrigin(exposedHeaders = "authorization")
    @GetMapping(value = "wallet",produces = "application/json")
    public HashMap<String, Object> viewWallet(HttpServletRequest request){
        Principal requester = tokenService.extractRequesterDetails(request.getHeader("Authorization"));
        HashMap<String, Object> response = new HashMap<String, Object>();
        double walletBalance = userService.viewWallet(requester.getUserId());
        response.put("wallet balance: ", walletBalance);
        return response;
    }


    //-----------------------------------------------------------------------
    //Exceptions

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HashMap<String, Object> handleInvalidRequests(InvalidRequestException e) {
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", 400);
        responseBody.put("message", e.getMessage());
        responseBody.put("timestamp", LocalDateTime.now());
        return responseBody;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public HashMap<String, Object> handleAuthenticationException(AuthenticationException e) {
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", 401);
        responseBody.put("message", e.getMessage());
        responseBody.put("timestamp", LocalDateTime.now());
        return responseBody;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public HashMap<String, Object> handleForbiddenException(ForbiddenException e) {
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", 403);
        responseBody.put("message", e.getMessage());
        responseBody.put("timestamp", LocalDateTime.now());
        return responseBody;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public HashMap<String, Object> handleResourceConflictExceptions(ResourceConflictException e) {
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", 409);
        responseBody.put("message", e.getMessage());
        responseBody.put("timestamp", LocalDateTime.now());
        return responseBody;
    }
}
