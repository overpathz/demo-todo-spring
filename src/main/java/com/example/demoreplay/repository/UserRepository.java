package com.example.demoreplay.repository;

import com.example.demoreplay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    @Query(nativeQuery = true, value = "select count(*) from users where role = 'ROLE_ADMIN'")
    int getUsersWithAdminRole();
}
