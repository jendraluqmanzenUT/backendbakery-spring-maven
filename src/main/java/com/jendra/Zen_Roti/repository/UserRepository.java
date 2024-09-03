package com.jendra.Zen_Roti.repository;

import com.jendra.Zen_Roti.entity.Address;
import com.jendra.Zen_Roti.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);

}