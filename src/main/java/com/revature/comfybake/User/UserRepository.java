package com.revature.comfybake.User;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, String> {
    @Query(
            value = "SELECT * from comfy_bake.users where username = ?1",
            nativeQuery = true
    )
    User getUserByUsername(String username);


    @Query(
            value = "SELECT * from comfy_bake.users where user_id = ?1",
            nativeQuery = true
    )
    User getUserByUserId(String id);

}
