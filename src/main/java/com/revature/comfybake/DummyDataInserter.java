package com.revature.comfybake;

import com.revature.comfybake.User.Profile.UserProfile;
import com.revature.comfybake.User.Profile.UserProfileRepository;
import com.revature.comfybake.User.Role.UserRole;
import com.revature.comfybake.User.Role.UserRoleRepository;
import com.revature.comfybake.User.User;
import com.revature.comfybake.User.UserRepository;
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

    @Autowired
    public DummyDataInserter(UserRepository userRepository, UserRoleRepository userRoleRepository, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userProfileRepository = userProfileRepository;
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
        user1.setPassword("123456");
        user1.setUserRole(customer);
        user1.setUserProfile(user1Profile);
        userRepository.save(user1);
    }

}
