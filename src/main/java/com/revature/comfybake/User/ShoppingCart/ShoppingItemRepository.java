package com.revature.comfybake.User.ShoppingCart;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingItemRepository extends CrudRepository<ShoppingItem,String> {

    @Query(
            value="SELECT * from comfy_bake.shopping_items where shopping_list_id =?1 order by completed_time desc",
            nativeQuery = true
    )
    ShoppingItem[] getShoppingItemByShoppingListId(String id);
}
