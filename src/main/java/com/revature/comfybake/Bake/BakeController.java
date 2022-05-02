package com.revature.comfybake.Bake;

import com.revature.comfybake.Bake.dtos.NewBakeRequest;
import com.revature.comfybake.Bake.dtos.PurchaseRequest;
import com.revature.comfybake.Principal;
import com.revature.comfybake.Token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@CrossOrigin
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
        System.out.println("requester.getUserId() "+requester.getUserId());
        bakeService.purchaseBakes(purchaseRequests.getPurchaseItems(), requester.getUserId());
        return response;
    }
}
