package com.revature.comfybake.Bake;


import com.revature.comfybake.Bake.dtos.NewBakeRequest;
import com.revature.comfybake.Bake.dtos.PurchaseItem;
import com.revature.comfybake.User.Profile.UserProfile;
import com.revature.comfybake.User.Profile.UserProfileRepository;
import com.revature.comfybake.User.User;
import com.revature.comfybake.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BakeService {
    private BakeRepository bakeRepository;
    private UserRepository userRepository;
    private UserProfileRepository userProfileRepository;

    @Autowired
    public BakeService(BakeRepository bakeRepository, UserRepository userRepository, UserProfileRepository userProfileRepository) {
        this.bakeRepository = bakeRepository;
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
    }

    //Baker: Selling baked goods
    public void addBake(NewBakeRequest newBakeRequest, String username){
        User currentUser = userRepository.getUserByUsername(username);
        UserProfile currentUserProfile = userProfileRepository.findById(currentUser.getUserProfile().getUserProfileId()).get();

        Bake newbake = new Bake();
        newbake.setBakeId(UUID.randomUUID().toString());
        newbake.setBakeName(newBakeRequest.getBakeName());
        newbake.setImage(newBakeRequest.getImage());
        newbake.setDescribed(newBakeRequest.getDescribed());
        newbake.setPrice(newBakeRequest.getPrice());
        newbake.setQuantity(newBakeRequest.getQuantity());
        newbake.setRecipe(newBakeRequest.getRecipe());
        newbake.setUserProfile(currentUserProfile);
        bakeRepository.save(newbake);
    }

    //Customer: purchase baked goods
    public void purchaseBakes(PurchaseItem[] purchaseItems, String customerId){
        double totalPrice = 0;

        for(PurchaseItem purchaseItem : purchaseItems) {
            Bake bake = bakeRepository.findById(purchaseItem.getBakeId()).get();
            if (purchaseItem.getQuantity() <= bake.getQuantity()) {
                totalPrice += purchaseItem.getQuantity() * bake.getPrice();
                bake.setQuantity(bake.getQuantity() - purchaseItem.getQuantity());
                bakeRepository.save(bake);
            }
        }
        System.out.println("totalPrice: "+totalPrice);
    }
}
