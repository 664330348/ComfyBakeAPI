package com.revature.comfybake.User.ShoppingCart;

import javax.persistence.*;

@Entity
@Table(name="shopping_list")
public class ShoppingList {
    @Id
    @Column(name="shopping_list_id")
    private String shoppingListId;

    public ShoppingList() {
        super();
    }

    public ShoppingList(String shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(String shoppingListId) {
        this.shoppingListId = shoppingListId;
    }
}
