package com.revature.comfybake.User;

import com.revature.comfybake.User.Profile.UserProfile;
import com.revature.comfybake.User.Profile.UserProfileRepository;
import com.revature.comfybake.User.Role.UserRole;
import com.revature.comfybake.User.Role.UserRoleRepository;
import com.revature.comfybake.User.dtos.LoginRequest;
import com.revature.comfybake.User.dtos.NewUserRequest;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private  UserRepository userRepository;
    private  UserRoleRepository userRoleRepository;
    private  UserProfileRepository userProfileRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userProfileRepository = userProfileRepository;
    }

    //Register a new user as a customer
    public User register(NewUserRequest newUserRequest){
        //boolean usernameAvailable = isUsernameAvailable(newUserRequest.getUsername());


        /*if (!usernameAvailable || !emailAvailable) {
            String msg = "The values provided for the following fields are already taken by other users: ";
            if (!usernameAvailable) msg += "username ";
            if (!emailAvailable) msg += "email";
            throw new RuntimeException(msg);
        }*/
        UserRole userRole = userRoleRepository.getUserRoleByRole("Customer").get();
        UserProfile  userProfile = new UserProfile();
        userProfile.setUserProfileId(UUID.randomUUID().toString());
        userProfile.setFirstName(newUserRequest.getFirstName());
        userProfile.setLastName(newUserRequest.getLastName());

        userProfileRepository.save(userProfile);

        User newUser = new User();
        newUser.setUserId(UUID.randomUUID().toString());
        newUser.setUsername(newUserRequest.getUsername());
        newUser.setPassword(BCrypt.hashpw(newUserRequest.getPassword(), BCrypt.gensalt(10)));
        newUser.setUserRole(userRole);
        newUser.setUserProfile(userProfile);
        userRepository.save(newUser);

        return  newUser;
    }

    //Login
    public User login(LoginRequest loginRequest){

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        User user = userRepository.getUserByUsername(username);
        return user;
    }


    public boolean isEmailValid(String email) {
        return email.matches("^[^@\\s]+@[^@\\s.]+\\.[^@.\\s]+$");
    }

    public boolean isUsernameValid(String username) {
        if (username == null) return false;
        return username.matches("^[a-zA-Z0-9]{8,25}");
    }

    public boolean isPasswordValid(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }

    /*public boolean isUsernameAvailable(String username) {
        return !userRepository.getUserByUsername(username).isPresent();
    }*/

    /*public boolean isEmailAvailable(String email) {
        return userRepository.getUserByEmail(email) == null;
    }*/
}
