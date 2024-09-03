package com.jendra.Zen_Roti.repository;

import com.jendra.Zen_Roti.entity.CartItem;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
