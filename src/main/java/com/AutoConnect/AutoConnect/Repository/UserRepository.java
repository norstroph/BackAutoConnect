package com.AutoConnect.AutoConnect.Repository;

import com.AutoConnect.AutoConnect.Entity.Role;
import com.AutoConnect.AutoConnect.Entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<List<User>> findByRole(Role role);
    Optional<User> findById(Integer id);
}
