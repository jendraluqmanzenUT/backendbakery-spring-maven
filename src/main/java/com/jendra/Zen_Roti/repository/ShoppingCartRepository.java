package com.jendra.Zen_Roti.repository;

import com.jendra.Zen_Roti.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
}
