package com.revature.comfybake.Bake;


import com.revature.comfybake.Bake.dtos.NewBakeRequest;
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

    //Add a new bake by the baker
    public void AddBake(NewBakeRequest newBakeRequest, String username){
        User currentUser = userRepository.getUserByUsername(username);
        System.out.println(" currentUser  "+currentUser);
        UserProfile currentUserProfile = userProfileRepository.findById(currentUser.getUserProfile().getUserProfileId()).get();
        System.out.println(" currentUserProfile  "+currentUserProfile);

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
}
