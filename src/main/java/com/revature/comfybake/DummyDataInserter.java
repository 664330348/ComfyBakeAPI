package com.revature.comfybake;

import com.revature.comfybake.Bake.Bake;
import com.revature.comfybake.Bake.BakeRepository;
import com.revature.comfybake.User.Orders.OrderHistory;
import com.revature.comfybake.User.Orders.OrderHistoryRepository;
import com.revature.comfybake.User.Orders.OrderItem;
import com.revature.comfybake.User.Orders.OrderItemRepository;
import com.revature.comfybake.User.Profile.UserProfile;
import com.revature.comfybake.User.Profile.UserProfileRepository;
import com.revature.comfybake.User.Role.UserRole;
import com.revature.comfybake.User.Role.UserRoleRepository;
import com.revature.comfybake.User.User;
import com.revature.comfybake.User.UserRepository;
import com.revature.comfybake.User.Wallet.UserWallet;
import com.revature.comfybake.User.Wallet.UserWalletRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class DummyDataInserter implements CommandLineRunner{
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserProfileRepository userProfileRepository;
    private final BakeRepository bakeRepository;
    private final UserWalletRepository userWalletRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public DummyDataInserter(UserRepository userRepository, UserRoleRepository userRoleRepository, UserProfileRepository userProfileRepository, BakeRepository bakeRepository, UserWalletRepository userWalletRepository, OrderHistoryRepository orderHistoryRepository, OrderItemRepository orderItemRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userProfileRepository = userProfileRepository;
        this.bakeRepository = bakeRepository;
        this.userWalletRepository = userWalletRepository;
        this.orderHistoryRepository = orderHistoryRepository;
        this.orderItemRepository = orderItemRepository;
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
        user1Profile.setFirstname("FName");
        user1Profile.setLastname("LName");
        userProfileRepository.save(user1Profile);

        UserWallet userWallet1 = new UserWallet();
        userWallet1.setWalletId(UUID.randomUUID().toString());
        userWalletRepository.save(userWallet1);

        OrderHistory orderHistory = new OrderHistory();
        String orderHistoryId = UUID.randomUUID().toString();
        orderHistory.setOrderHistoryItem(orderHistoryId);
        orderHistoryRepository.save(orderHistory);

        User user1 = new User();
        user1.setUserId(UUID.randomUUID().toString());
        user1.setUsername("user1user1");
        user1.setPassword(BCrypt.hashpw("Revature?123", BCrypt.gensalt(10)));
        user1.setUserRole(baker);
        user1.setUserProfile(user1Profile);
        user1.setUserWallet(userWallet1);
        user1.setOrderHistory(orderHistory);
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

        String orderGroupId = UUID.randomUUID().toString();
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(UUID.randomUUID().toString());
        orderItem.setItemName("bake1");
        orderItem.setItemImage("imageUrl1");
        orderItem.setItemPrice(18.99);
        orderItem.setQuantity(3);
        orderItem.setTotalCost(56.97);
        orderItem.setCompletedTime(LocalDateTime.now());
        orderItem.setOrderHistory(orderHistory);
        orderItem.setGroupId(orderGroupId);
        orderItemRepository.save(orderItem);
    }

}
