package com.revature.comfybake.User.Wallet;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserWalletRepository extends CrudRepository<UserWallet,String> {
    @Query(
            value = "SELECT * from comfy_bake.user_wallet where user_wallet_id = ?1",
            nativeQuery = true
    )
    UserWallet getUserWalletByUserWalletId(String id);
}
