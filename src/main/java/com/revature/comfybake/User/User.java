package com.revature.comfybake.User;

import com.revature.comfybake.User.Orders.OrderHistory;
import com.revature.comfybake.User.Profile.UserProfile;
import com.revature.comfybake.User.Role.UserRole;
import com.revature.comfybake.User.ShoppingCart.ShoppingList;
import com.revature.comfybake.User.Wallet.UserWallet;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name="user_id")
    private String userId;

    @Column(name="username", nullable = false)
    private String Username;

    @Column(name="password", nullable = false)
    private String Password;

    @ManyToOne
    @JoinColumn(name="user_role_id", nullable = false)
    private UserRole userRole;

    @OneToOne
    @JoinColumn(name="user_profile_id", nullable = false, unique = true)
    private UserProfile userProfile;

    @OneToOne
    @JoinColumn(name="user_wallet_id", nullable = false, unique = true)
    private UserWallet userWallet;

    @OneToOne
    @JoinColumn(name="order_history_id", nullable = false, unique = true)
    private OrderHistory orderHistory;

    @OneToOne
    @JoinColumn(name="shopping_list_id", nullable = false, unique = true)
    private ShoppingList shoppingList;

    public User() {
        super();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public UserWallet getUserWallet() {
        return userWallet;
    }

    public void setUserWallet(UserWallet userWallet) {
        this.userWallet = userWallet;
    }

    public OrderHistory getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(OrderHistory orderHistory) {
        this.orderHistory = orderHistory;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }
}
