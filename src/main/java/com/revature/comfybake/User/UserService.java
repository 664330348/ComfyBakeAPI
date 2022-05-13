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
import com.revature.comfybake.exceptions.AuthenticationException;
import com.revature.comfybake.exceptions.InvalidRequestException;
import com.revature.comfybake.exceptions.ResourceConflictException;
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
        if(!isUsernameValid(newUserRequest.getUsername())){
            throw new InvalidRequestException("Invalid Username!");
        }else if (!isPasswordValid(newUserRequest.getPassword())){
            throw new InvalidRequestException("Invalid Password!");
        }else if (newUserRequest.getEmail()!=null && !isEmailValid(newUserRequest.getEmail())){
            throw new InvalidRequestException("Invalid Email!");
        } else if(!isUsernameAvailable(newUserRequest.getUsername())){
            throw new ResourceConflictException("The username are already taken by other users");
        }

        UserRole userRole = userRoleRepository.getUserRoleByRole("Customer").get();
        UserProfile  userProfile = new UserProfile();
        userProfile.setUserProfileId(UUID.randomUUID().toString());
        userProfile.setFirstname(newUserRequest.getFirstName());
        userProfile.setLastname(newUserRequest.getLastName());
        if(newUserRequest.getEmail()!=null){
            userProfile.setEmail(newUserRequest.getEmail());
        }
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
        if(!isUsernameValid(loginRequest.getUsername())){
            throw new InvalidRequestException("Invalid Username!");
        }else if (!isPasswordValid(loginRequest.getPassword())) {
            throw new InvalidRequestException("Invalid Password!");
        }else if(!isUserPresent(loginRequest.getUsername())){
            throw new InvalidRequestException("No Users Found!");
        }

        User user = userRepository.getUserByUsername(loginRequest.getUsername());
        if(!BCrypt.checkpw(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidRequestException("Wrong Password!");
        }

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

        if(updateProfileRequest.getEmail() != null && updateProfileRequest.getEmail().length()>0){
            if (!isEmailValid(updateProfileRequest.getEmail())) {
                throw new InvalidRequestException("Invalid Email!");
            }
            userProfile.setEmail(updateProfileRequest.getEmail());
        }
        if(updateProfileRequest.getPhoto() != null && updateProfileRequest.getPhoto().length()>0){
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

    public boolean isUsernameAvailable(String username) {
        if(userRepository.getUserByUsername(username) == null) return  true;
        return false;
    }

    public boolean isUserPresent(String username){
        if(userRepository.getUserByUsername(username) == null) return  false;
        return true;
    }


}
