package com.revature.comfybake.User.Wallet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserWalletRepository extends CrudRepository<UserWallet,String> {

}
