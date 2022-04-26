package com.revature.comfybake.User.Role;

import com.revature.comfybake.User.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, String> {
    @Query("from UserRole ur where ur.role = ?1")
    Optional<UserRole> getUserRoleByRole(String role);
}
