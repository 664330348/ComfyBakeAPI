package com.revature.comfybake.Bake;

import com.revature.comfybake.Bake.dtos.NewBakeRequest;
import com.revature.comfybake.Bake.dtos.PurchaseRequest;
import com.revature.comfybake.Principal;
import com.revature.comfybake.Token.TokenService;
import com.revature.comfybake.User.dtos.OrderHistoryResponse;
import com.revature.comfybake.exceptions.AuthenticationException;
import com.revature.comfybake.exceptions.ForbiddenException;
import com.revature.comfybake.exceptions.InvalidRequestException;
import com.revature.comfybake.exceptions.ResourceConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(exposedHeaders = "authorization")
@RestController
@RequestMapping("/baked-goods")
public class BakeController {
    private BakeService bakeService;
    private TokenService tokenService;

    @Autowired
    public BakeController(BakeService bakeService, TokenService tokenService) {
        this.bakeService = bakeService;
        this.tokenService = tokenService;
    }


    //Baker: Selling baked goods
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = "application/json")
    public HashMap<String, Object>addBakedGoods(@RequestBody NewBakeRequest newBakeRequest, HttpServletRequest request){
        Principal requester = tokenService.extractRequesterDetails(request.getHeader("Authorization"));
        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("role:",requester.getRole());
        if(!requester.getRole().equals("Baker")){
            response.put("endpoint", " /baked-goods");
            response.put("status", "Forbidden");
            response.put("providedValues","you are not allowed to do so.");
        }else if (requester.getRole().equals("Baker")){
            bakeService.addBake(newBakeRequest, requester.getUsername());
            response.put("endpoint", " /baked-goods");
            response.put("status", "created");
            response.put("providedValues","you are allowed to do so.");
        }
        return response;
    }

    //Customer: purchase baked goods
    @PutMapping(produces = "application/json")
    public HashMap<String, Object>purchaseBakedGoods(@RequestBody PurchaseRequest purchaseRequests, HttpServletRequest request){
        Principal requester = tokenService.extractRequesterDetails(request.getHeader("Authorization"));
        HashMap<String, Object> response = new HashMap<String, Object>();
        bakeService.purchaseBakes(purchaseRequests.getPurchaseItems(), requester.getUserId());
        return response;
    }

    //View Order History
    @GetMapping(value = "history", produces = "application/json")
    public HashMap<String, Object> viewOrderHistory(HttpServletRequest request){
        Principal requester = tokenService.extractRequesterDetails(request.getHeader("Authorization"));
        HashMap<String, Object> response = new HashMap<String, Object>();
        ArrayList<OrderHistoryResponse> orderHistoryResponses = bakeService.viewOrderHistory(requester.getUserId());
        response.put("OrderHistoryResponses",orderHistoryResponses);
        return response;
    }

    //View All Baked Goods
    @GetMapping(produces = "application/json")
    public HashMap<String, Object> viewAllBakedGoods(HttpServletRequest request){
        Principal requester = tokenService.extractRequesterDetails(request.getHeader("Authorization"));
        if(requester == null){
            throw new AuthenticationException();
        }
        HashMap<String, Object> response = new HashMap<String, Object>();
        List<Bake> productResponses = bakeService.viewAllBakedGoods();
        response.put("AllBakedGoods",productResponses);
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
