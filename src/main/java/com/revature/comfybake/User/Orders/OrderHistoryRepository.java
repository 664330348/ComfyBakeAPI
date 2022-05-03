package com.revature.comfybake.User.Orders;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHistoryRepository extends CrudRepository<OrderHistory,String> {

}
