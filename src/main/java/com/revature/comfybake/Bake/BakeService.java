package com.revature.comfybake.Bake;


import com.revature.comfybake.Bake.dtos.NewBakeRequest;
import com.revature.comfybake.Bake.dtos.PurchaseItem;
import com.revature.comfybake.User.Profile.UserProfile;
import com.revature.comfybake.User.Profile.UserProfileRepository;
import com.revature.comfybake.User.User;
import com.revature.comfybake.User.UserRepository;
import com.revature.comfybake.User.Wallet.UserWallet;
import com.revature.comfybake.User.Wallet.UserWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BakeService {
    private BakeRepository bakeRepository;
    private UserRepository userRepository;
    private UserProfileRepository userProfileRepository;
    private UserWalletRepository userWalletRepository;

    @Autowired
    public BakeService(BakeRepository bakeRepository, UserRepository userRepository, UserProfileRepository userProfileRepository, UserWalletRepository userWalletRepository) {
        this.bakeRepository = bakeRepository;
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.userWalletRepository = userWalletRepository;
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
        User customer = userRepository.getUserByUserId(customerId);
        UserWallet userWallet = userWalletRepository.getUserWalletByUserWalletId(customer.getUserWallet().getWalletId());

        for(PurchaseItem purchaseItem : purchaseItems) {
            Bake bake = bakeRepository.findById(purchaseItem.getBakeId()).get();
            if (purchaseItem.getQuantity() <= bake.getQuantity()) {
                totalPrice += purchaseItem.getQuantity() * bake.getPrice();
                bake.setQuantity(bake.getQuantity() - purchaseItem.getQuantity());
                bakeRepository.save(bake);
            }
        }
        userWallet.setTotalBalance(userWallet.getTotalBalance()-totalPrice);
        userWalletRepository.save(userWallet);
    }
}
