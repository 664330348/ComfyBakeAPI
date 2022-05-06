package com.revature.comfybake.User.Orders;

import com.revature.comfybake.User.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem,String> {
    @Query(
            value = "SELECT * from comfy_bake.order_items where order_history_id = ?1 order by completed_time desc",
            nativeQuery = true
    )
    OrderItem[] getOrderItemByOrderHistoryId(String id);
}
