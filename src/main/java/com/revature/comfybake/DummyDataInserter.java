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
import com.revature.comfybake.User.ShoppingCart.ShoppingList;
import com.revature.comfybake.User.ShoppingCart.ShoppingListRepository;
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
    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public DummyDataInserter(UserRepository userRepository, UserRoleRepository userRoleRepository, UserProfileRepository userProfileRepository, BakeRepository bakeRepository, UserWalletRepository userWalletRepository, OrderHistoryRepository orderHistoryRepository, OrderItemRepository orderItemRepository, ShoppingListRepository shoppingListRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userProfileRepository = userProfileRepository;
        this.bakeRepository = bakeRepository;
        this.userWalletRepository = userWalletRepository;
        this.orderHistoryRepository = orderHistoryRepository;
        this.orderItemRepository = orderItemRepository;
        this.shoppingListRepository = shoppingListRepository;
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

        //-----------baker1----------------------------------
        UserProfile baker1Profile = new UserProfile();
        baker1Profile.setUserProfileId(UUID.randomUUID().toString());
        baker1Profile.setFirstname("David");
        baker1Profile.setLastname("Furman");
        userProfileRepository.save(baker1Profile);

        UserWallet baker1Wallet = new UserWallet();
        baker1Wallet.setWalletId(UUID.randomUUID().toString());
        userWalletRepository.save(baker1Wallet);

        OrderHistory baker1OrderHistory = new OrderHistory();
        baker1OrderHistory.setOrderHistoryItem(UUID.randomUUID().toString());
        orderHistoryRepository.save(baker1OrderHistory);

        ShoppingList baker1ShoppingList = new ShoppingList();
        baker1ShoppingList.setShoppingListId(UUID.randomUUID().toString());
        shoppingListRepository.save(baker1ShoppingList);

        User baker1 = new User();
        baker1.setUserId(UUID.randomUUID().toString());
        baker1.setUsername("baker1baker1");
        baker1.setPassword(BCrypt.hashpw("Revature?123", BCrypt.gensalt(10)));
        baker1.setUserRole(baker);
        baker1.setUserProfile(baker1Profile);
        baker1.setUserWallet(baker1Wallet);
        baker1.setOrderHistory(baker1OrderHistory);
        baker1.setShoppingList(baker1ShoppingList);
        userRepository.save(baker1);

        Bake bake1 = new Bake();
        bake1.setBakeId(UUID.randomUUID().toString());
        bake1.setBakeName("Mini Cherry Bakewell Tarts");
        bake1.setImage("https://www.crumbblog.com/wp-content/uploads/2012/12/xmasjamtarts_verticalsm.jpg");
        bake1.setDescribed("A British classic goes bite-sized in these adorable mini tarts filled with jam and frangipane and covered with a snowy white icing.");
        bake1.setPrice(3.99);
        bake1.setQuantity(99);
        bake1.setRecipe("all-purpose flour, cold butter, egg yolks");
        bake1.setUserProfile(baker1Profile);
        bakeRepository.save(bake1);

        Bake bake2 = new Bake();
        bake2.setBakeId(UUID.randomUUID().toString());
        bake2.setBakeName("Maple Sugar Scrolls");
        bake2.setImage("https://c1.staticflickr.com/5/4264/35392510042_71593c4592.jpg");
        bake2.setDescribed("These soft, fluffy buns swap out the usual cinnamon for the delicate sweetness of maple sugar.");
        bake2.setPrice(6.99);
        bake2.setQuantity(99);
        bake2.setRecipe("milk, eggs, maple sugar, vanilla extract");
        bake2.setUserProfile(baker1Profile);
        bakeRepository.save(bake2);

        Bake bake3 = new Bake();
        bake3.setBakeId(UUID.randomUUID().toString());
        bake3.setBakeName("Blood Orange Canneles");
        bake3.setImage("https://c1.staticflickr.com/9/8791/17270953355_3b4882da06.jpg");
        bake3.setDescribed("This twist on the traditional French cannelé gets its sweet, citrusy flavour from a combination of blood orange zest and orange liqueur.");
        bake3.setPrice(1.33);
        bake3.setQuantity(199);
        bake3.setRecipe("whole milk, egg yolk, blood orange, unsalted butter");
        bake3.setUserProfile(baker1Profile);
        bakeRepository.save(bake3);

        Bake bake4 = new Bake();
        bake4.setBakeId(UUID.randomUUID().toString());
        bake4.setBakeName("Peanut Butter Nanaimo Bars");
        bake4.setImage("https://www.justsotasty.com/wp-content/uploads/2020/10/Peanut-Butter-Nanaimo-Bars-7-500x375.jpg");
        bake4.setDescribed("The classic Nanaimo bar gets an upgrade with a sweet, smooth peanut butter filling.");
        bake4.setPrice(4.19);
        bake4.setQuantity(99);
        bake4.setRecipe("sugar, cocoa, chocolate wafer crumbs, graham cracker crumbs");
        bake4.setUserProfile(baker1Profile);
        bakeRepository.save(bake4);

        Bake bake5 = new Bake();
        bake5.setBakeId(UUID.randomUUID().toString());
        bake5.setBakeName("Fresh Raspberry Custard Tarts");
        bake5.setImage("https://img.taste.com.au/VvYgP92t/taste/2016/11/raspberry-custard-tarts-103738-1.jpeg");
        bake5.setDescribed("These elegant little tarts pair a buttery shortcrust pastry base with a buttermilk custard filling.");
        bake5.setPrice(3.56);
        bake5.setQuantity(99);
        bake5.setRecipe("all-purpose flour, eggs, fresh raspberries, whipping cream");
        bake5.setUserProfile(baker1Profile);
        bakeRepository.save(bake5);
        //---------------------------------------------------


        //-----------baker2----------------------------------
        UserProfile baker2Profile = new UserProfile();
        baker2Profile.setUserProfileId(UUID.randomUUID().toString());
        baker2Profile.setFirstname("Mary");
        baker2Profile.setLastname("Walker");
        userProfileRepository.save(baker2Profile);

        UserWallet baker2Wallet = new UserWallet();
        baker2Wallet.setWalletId(UUID.randomUUID().toString());
        userWalletRepository.save(baker2Wallet);

        OrderHistory baker2OrderHistory = new OrderHistory();
        baker2OrderHistory.setOrderHistoryItem(UUID.randomUUID().toString());
        orderHistoryRepository.save(baker2OrderHistory);

        ShoppingList baker2ShoppingList = new ShoppingList();
        baker2ShoppingList.setShoppingListId(UUID.randomUUID().toString());
        shoppingListRepository.save(baker2ShoppingList);

        User baker2 = new User();
        baker2.setUserId(UUID.randomUUID().toString());
        baker2.setUsername("baker2baker2");
        baker2.setPassword(BCrypt.hashpw("Revature?123", BCrypt.gensalt(10)));
        baker2.setUserRole(baker);
        baker2.setUserProfile(baker2Profile);
        baker2.setUserWallet(baker2Wallet);
        baker2.setOrderHistory(baker2OrderHistory);
        baker2.setShoppingList(baker2ShoppingList);
        userRepository.save(baker2);

        Bake bake6 = new Bake();
        bake6.setBakeId(UUID.randomUUID().toString());
        bake6.setBakeName("Peanut Butter and Jelly Thumbprints");
        bake6.setImage("https://bakeorbreak.com/wp-content/uploads/2012/06/pbjcks1kR2.jpg");
        bake6.setDescribed("These pretty little thumbprint cookies pack all of the goodness of an old-school PB&J sandwich.");
        bake6.setPrice(2.99);
        bake6.setQuantity(99);
        bake6.setRecipe("baking soda, baking powder, eggs, creamy peanut butter, grape jelly");
        bake6.setUserProfile(baker2Profile);
        bakeRepository.save(bake6);

        Bake bake7 = new Bake();
        bake7.setBakeId(UUID.randomUUID().toString());
        bake7.setBakeName("Savoury Cheddar Coins");
        bake7.setImage("https://img.apmcdn.org/e20d5a3b7b2826ab25839452412073dfe41a1a03/uncropped/079faf-splendid-table-sfs-cheddar-cheese-coins-12-lede.jpg");
        bake7.setDescribed("This cheesy twist on the classic icebox cookie is perfect for the holidays.");
        bake7.setPrice(0.69);
        bake7.setQuantity(199);
        bake7.setRecipe("whole milk, egg yolk, blood orange, unsalted butter");
        bake7.setUserProfile(baker2Profile);
        bakeRepository.save(bake7);

        Bake bake8 = new Bake();
        bake8.setBakeId(UUID.randomUUID().toString());
        bake8.setBakeName("Pumpkin Cheesecake Swirl Brownies");
        bake8.setImage("https://chocolatewithgrace.com/wp-content/uploads/2018/09/CWG-Pumpkin-Cheesecake-Brownies-7-1-of-1.jpg");
        bake8.setDescribed("It's tough having to choose between fudgey brownies and tangy pumpkin cheesecake.");
        bake8.setPrice(3.99);
        bake8.setQuantity(99);
        bake8.setRecipe("unsweetened chocolate, eggs, pumpkin puree, brown sugar");
        bake8.setUserProfile(baker2Profile);
        bakeRepository.save(bake8);
        //--------------------------------------------------



        //-----------customer1-----------------------------
        UserProfile user1Profile = new UserProfile();
        user1Profile.setUserProfileId(UUID.randomUUID().toString());
        user1Profile.setFirstname("Luck");
        user1Profile.setLastname("Chen");
        userProfileRepository.save(user1Profile);

        UserWallet userWallet1 = new UserWallet();
        userWallet1.setWalletId(UUID.randomUUID().toString());
        userWalletRepository.save(userWallet1);

        OrderHistory orderHistory = new OrderHistory();
        String orderHistoryId = UUID.randomUUID().toString();
        orderHistory.setOrderHistoryItem(orderHistoryId);
        orderHistoryRepository.save(orderHistory);

        ShoppingList user1ShoppingList = new ShoppingList();
        user1ShoppingList.setShoppingListId(UUID.randomUUID().toString());
        shoppingListRepository.save(user1ShoppingList);

        User user1 = new User();
        user1.setUserId(UUID.randomUUID().toString());
        user1.setUsername("user1user1");
        user1.setPassword(BCrypt.hashpw("Revature?123", BCrypt.gensalt(10)));
        user1.setUserRole(baker);
        user1.setUserProfile(user1Profile);
        user1.setUserWallet(userWallet1);
        user1.setOrderHistory(orderHistory);
        user1.setShoppingList(user1ShoppingList);
        userRepository.save(user1);

        String orderGroupId = UUID.randomUUID().toString();
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(UUID.randomUUID().toString());
        orderItem.setItemName("Fresh Raspberry Custard Tarts");
        orderItem.setItemImage("https://img.taste.com.au/VvYgP92t/taste/2016/11/raspberry-custard-tarts-103738-1.jpeg");
        orderItem.setItemPrice(3.56);
        orderItem.setQuantity(3);
        orderItem.setTotalCost(10.68);
        orderItem.setCompletedTime(LocalDateTime.now());
        orderItem.setOrderHistory(orderHistory);
        orderItem.setGroupId(orderGroupId);
        orderItemRepository.save(orderItem);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setOrderItemId(UUID.randomUUID().toString());
        orderItem2.setItemName("Pumpkin Cheesecake Swirl Brownies");
        orderItem2.setItemImage("https://chocolatewithgrace.com/wp-content/uploads/2018/09/CWG-Pumpkin-Cheesecake-Brownies-7-1-of-1.jpg");
        orderItem2.setItemPrice(3.99);
        orderItem2.setQuantity(5);
        orderItem2.setTotalCost(19.95);
        orderItem2.setCompletedTime(LocalDateTime.now());
        orderItem2.setOrderHistory(orderHistory);
        orderItem2.setGroupId(orderGroupId);
        orderItemRepository.save(orderItem2);

        String orderGroupId2 = UUID.randomUUID().toString();
        OrderItem orderItem3 = new OrderItem();
        orderItem3.setOrderItemId(UUID.randomUUID().toString());
        orderItem3.setItemName("Savoury Cheddar Coins");
        orderItem3.setItemImage("https://img.apmcdn.org/e20d5a3b7b2826ab25839452412073dfe41a1a03/uncropped/079faf-splendid-table-sfs-cheddar-cheese-coins-12-lede.jpg");
        orderItem3.setItemPrice(0.69);
        orderItem3.setQuantity(10);
        orderItem3.setTotalCost(6.9);
        orderItem3.setCompletedTime(LocalDateTime.now());
        orderItem3.setOrderHistory(orderHistory);
        orderItem3.setGroupId(orderGroupId2);
        orderItemRepository.save(orderItem3);
    }

}
