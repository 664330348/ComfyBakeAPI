package com.revature.comfybake;

import com.revature.comfybake.Bake.Bake;
import com.revature.comfybake.Bake.BakeRepository;
import com.revature.comfybake.User.Profile.UserProfile;
import com.revature.comfybake.User.Profile.UserProfileRepository;
import com.revature.comfybake.User.Role.UserRole;
import com.revature.comfybake.User.Role.UserRoleRepository;
import com.revature.comfybake.User.User;
import com.revature.comfybake.User.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class DummyDataInserter implements CommandLineRunner{
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserProfileRepository userProfileRepository;
    private final BakeRepository bakeRepository;

    @Autowired
    public DummyDataInserter(UserRepository userRepository, UserRoleRepository userRoleRepository, UserProfileRepository userProfileRepository, BakeRepository bakeRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userProfileRepository = userProfileRepository;
        this.bakeRepository = bakeRepository;
    }

    @Override
    @Transactional
    public void run(String... args){
        //Create a table for User Role
        UserRole customer = new UserRole();
        customer.setUserRoleId(UUID.randomUUID().toString());
        customer.setRole("Customer");

        UserRole baker = new UserRole();
        baker.setUserRoleId(UUID.randomUUID().toString());
        baker.setRole("Baker");

        userRoleRepository.save(customer);
        userRoleRepository.save(baker);


        UserProfile user1Profile = new UserProfile();
        user1Profile.setUserProfileId(UUID.randomUUID().toString());
        user1Profile.setFirstName("FName");
        user1Profile.setLastName("LName");
        userProfileRepository.save(user1Profile);

        User user1 = new User();
        user1.setUserId(UUID.randomUUID().toString());
        user1.setUsername("user1");
        user1.setPassword(BCrypt.hashpw("123456", BCrypt.gensalt(10)));
        user1.setUserRole(baker);
        user1.setUserProfile(user1Profile);
        userRepository.save(user1);

        Bake bake1 = new Bake();
        bake1.setBakeId(UUID.randomUUID().toString());
        bake1.setBakeName("bake1");
        bake1.setImage("imageUrl");
        bake1.setDescribed("described");
        bake1.setPrice(18.99);
        bake1.setQuantity(5);
        bake1.setRecipe("recipe");
        bake1.setUserProfile(user1Profile);
        bakeRepository.save(bake1);

        Bake bake2 = new Bake();
        bake2.setBakeId(UUID.randomUUID().toString());
        bake2.setBakeName("bake2");
        bake2.setImage("imageUrl2");
        bake2.setDescribed("described2");
        bake2.setPrice(16.99);
        bake2.setQuantity(9);
        bake2.setRecipe("recipe2");
        bake2.setUserProfile(user1Profile);
        bakeRepository.save(bake2);

    }

}
