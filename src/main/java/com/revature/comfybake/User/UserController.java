package com.revature.comfybake.User;

import com.revature.comfybake.Principal;
import com.revature.comfybake.Token.TokenService;
import com.revature.comfybake.User.dtos.LoginRequest;
import com.revature.comfybake.User.dtos.NewUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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


    @CrossOrigin(exposedHeaders = "authorization")
    @PostMapping(value="login", produces = "application/json", consumes = "application/json")
    public Principal login(@RequestBody LoginRequest request, HttpServletResponse response) {
        Principal principal = new Principal(userService.login(request));

        String token = tokenService.generateToken(principal);
        response.setHeader("authorization", token);
        response.setStatus(201);

        return principal;
    }
}
