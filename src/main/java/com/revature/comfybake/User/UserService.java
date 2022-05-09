package com.revature.comfybake.User;

import com.revature.comfybake.User.Orders.OrderHistory;
import com.revature.comfybake.User.Orders.OrderHistoryRepository;
import com.revature.comfybake.User.Profile.UserProfile;
import com.revature.comfybake.User.Profile.UserProfileRepository;
import com.revature.comfybake.User.Role.UserRole;
import com.revature.comfybake.User.Role.UserRoleRepository;
import com.revature.comfybake.User.Wallet.UserWallet;
import com.revature.comfybake.User.Wallet.UserWalletRepository;
import com.revature.comfybake.User.dtos.LoginRequest;
import com.revature.comfybake.User.dtos.NewUserRequest;
import com.revature.comfybake.User.dtos.ProfileResponse;
import com.revature.comfybake.User.dtos.UpdateProfileRequest;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private  UserRepository userRepository;
    private  UserRoleRepository userRoleRepository;
    private  UserProfileRepository userProfileRepository;
    private  UserWalletRepository userWalletRepository;
    private  OrderHistoryRepository orderHistoryRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, UserProfileRepository userProfileRepository, UserWalletRepository userWalletRepository, OrderHistoryRepository orderHistoryRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userProfileRepository = userProfileRepository;
        this.userWalletRepository = userWalletRepository;
        this.orderHistoryRepository = orderHistoryRepository;
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
        userProfile.setFirstname(newUserRequest.getFirstName());
        userProfile.setLastname(newUserRequest.getLastName());
        userProfileRepository.save(userProfile);

        UserWallet userWallet = new UserWallet();
        userWallet.setWalletId(UUID.randomUUID().toString());
        userWalletRepository.save(userWallet);

        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrderHistoryItem(UUID.randomUUID().toString());
        orderHistoryRepository.save(orderHistory);

        User newUser = new User();
        newUser.setUserId(UUID.randomUUID().toString());
        newUser.setUsername(newUserRequest.getUsername());
        newUser.setPassword(BCrypt.hashpw(newUserRequest.getPassword(), BCrypt.gensalt(10)));
        newUser.setUserRole(userRole);
        newUser.setUserProfile(userProfile);
        newUser.setUserWallet(userWallet);
        newUser.setOrderHistory(orderHistory);
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

    //View User Profile
    public ProfileResponse viewUserProfile(String userId){
        User currentUser = userRepository.getUserByUserId(userId);
        UserProfile userProfile = userProfileRepository.
                findById(currentUser.getUserProfile().getUserProfileId()).get();

        ProfileResponse currentUserProfile = new ProfileResponse();
        currentUserProfile.setFirstname(userProfile.getFirstname());
        currentUserProfile.setLastname(userProfile.getLastname());
        if(userProfile.getEmail() != null){
            currentUserProfile.setEmail(userProfile.getEmail());
        }
        if(userProfile.getPhoto() != null){
            currentUserProfile.setPhoto(userProfile.getPhoto());
        }
        return  currentUserProfile;
    }

    //Update User Profile
    public void updateUserProfile(String userId, UpdateProfileRequest updateProfileRequest){
        User currentUser = userRepository.getUserByUserId(userId);
        UserProfile userProfile = userProfileRepository.
                findById(currentUser.getUserProfile().getUserProfileId()).get();
        if(updateProfileRequest.getFirstname() != null){
            userProfile.setFirstname(updateProfileRequest.getFirstname());
        }
        if(updateProfileRequest.getLastname() != null){
            userProfile.setLastname(updateProfileRequest.getLastname());
        }
        if(updateProfileRequest.getEmail() != null){
            userProfile.setEmail(updateProfileRequest.getEmail());
        }
        if(updateProfileRequest.getPhoto() != null){
            userProfile.setPhoto(updateProfileRequest.getPhoto());
        }
        userProfileRepository.save(userProfile);
    }

    //View User Wallet
    public double viewWallet(String userId){
        User currentUser = userRepository.getUserByUserId(userId);
        UserWallet userWallet = userWalletRepository.findById(currentUser.getUserWallet().getWalletId()).get();

        return userWallet.getTotalBalance();
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
