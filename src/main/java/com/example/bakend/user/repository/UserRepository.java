package com.example.bakend.user.repository;



import com.example.bakend.user.entity.Role;
import com.example.bakend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByMobile(String mobile);

    boolean existsByMobile(String mobile);

    boolean existsByEmail(String email);

    long countByVerifiedTrue();
    long countByRole(Role role);

}
