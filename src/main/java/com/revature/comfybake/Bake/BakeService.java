package com.revature.comfybake.Bake;


import com.revature.comfybake.Bake.dtos.NewBakeRequest;
import com.revature.comfybake.Bake.dtos.PurchaseItem;
import com.revature.comfybake.Principal;
import com.revature.comfybake.User.Orders.OrderHistory;
import com.revature.comfybake.User.Orders.OrderHistoryRepository;
import com.revature.comfybake.User.Orders.OrderItem;
import com.revature.comfybake.User.Orders.OrderItemRepository;
import com.revature.comfybake.User.Profile.UserProfile;
import com.revature.comfybake.User.Profile.UserProfileRepository;
import com.revature.comfybake.User.User;
import com.revature.comfybake.User.UserRepository;
import com.revature.comfybake.User.Wallet.UserWallet;
import com.revature.comfybake.User.Wallet.UserWalletRepository;
import com.revature.comfybake.User.dtos.OrderHistoryResponse;
import com.revature.comfybake.User.dtos.OrderItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class BakeService {
    private BakeRepository bakeRepository;
    private UserRepository userRepository;
    private UserProfileRepository userProfileRepository;
    private UserWalletRepository userWalletRepository;
    private OrderHistoryRepository orderHistoryRepository;
    private OrderItemRepository orderItemRepository;

    @Autowired
    public BakeService(BakeRepository bakeRepository, UserRepository userRepository, UserProfileRepository userProfileRepository, UserWalletRepository userWalletRepository, OrderHistoryRepository orderHistoryRepository, OrderItemRepository orderItemRepository) {
        this.bakeRepository = bakeRepository;
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.userWalletRepository = userWalletRepository;
        this.orderHistoryRepository = orderHistoryRepository;
        this.orderItemRepository = orderItemRepository;
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

        ArrayList<Bake> bakes = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();
        boolean canPurchase = true;
        for(PurchaseItem purchaseItem : purchaseItems) {
            Bake bake = bakeRepository.findById(purchaseItem.getBakeId()).get();
            double cost = purchaseItem.getQuantity() * bake.getPrice();
            if(userWallet.getTotalBalance() <totalPrice+cost || purchaseItem.getQuantity()>bake.getQuantity()
                || purchaseItem.getBakerProfileId().equals(customer.getUserProfile().getUserProfileId())){
                canPurchase = false;
                break;
            }
            totalPrice += cost;
            bake.setQuantity(bake.getQuantity() - purchaseItem.getQuantity());
            bakes.add(bake);
            quantities.add(purchaseItem.getQuantity());
        }
        if(canPurchase == true){
            String orderGroupId = UUID.randomUUID().toString();
            LocalDateTime now = LocalDateTime.now();
            OrderHistory orderHistory = customer.getOrderHistory();
            for(int i=0; i<bakes.size(); i++){
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderItemId(UUID.randomUUID().toString());
                orderItem.setItemName(bakes.get(i).getBakeName());
                orderItem.setItemImage(bakes.get(i).getImage());
                orderItem.setItemPrice(bakes.get(i).getPrice());
                orderItem.setQuantity(quantities.get(i));
                orderItem.setTotalCost(totalPrice);
                orderItem.setCompletedTime(now);
                orderItem.setOrderHistory(orderHistory);
                orderItem.setGroupId(orderGroupId);
                orderItemRepository.save(orderItem);
            }
        }

        userWallet.setTotalBalance(userWallet.getTotalBalance()-totalPrice);
        userWalletRepository.save(userWallet);
    }

    //view order history
    public ArrayList<OrderHistoryResponse> viewOrderHistory(String customerId){
        User customer = userRepository.getUserByUserId(customerId);
        OrderItem[] orderItems = orderItemRepository.getOrderItemByOrderHistoryId(customer.getOrderHistory().getOrderHistoryId());

        ArrayList<OrderHistoryResponse> orderHistoryResponses = new ArrayList<>();
        if(orderItems.length >0){
            int currentIndex = -1;
            String currentGroupId = "ZERO";

            for(OrderItem orderItem: orderItems){
                if(!orderItem.getGroupId().equals(currentGroupId)){
                    currentGroupId = orderItem.getGroupId();
                    currentIndex++;
                    OrderHistoryResponse currentOrderHistoryResponse = new OrderHistoryResponse();
                    currentOrderHistoryResponse.setCompletedTime(orderItem.getCompletedTime());
                    currentOrderHistoryResponse.setTotalCost(orderItem.getTotalCost());
                    orderHistoryResponses.add(currentOrderHistoryResponse);
                }
                OrderItemResponse orderItemResponse = new OrderItemResponse(orderItem.getItemName(),
                        orderItem.getItemImage(), orderItem.getItemPrice(), orderItem.getQuantity());
                orderHistoryResponses.get(currentIndex).addOrderItemResponses(orderItemResponse);
            }
        }
        return orderHistoryResponses;
    }
}
