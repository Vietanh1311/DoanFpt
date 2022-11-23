package com.company.demo.repository;

import com.company.demo.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query("SELECT SUM(c.total) FROM Cart c WHERE c.userId=?1")
    Long sumPrice(Long id);


    @Query("SELECT SUM(c.quantity) FROM Cart c WHERE c.userId=?1")
    Integer sumQuantity(Long id);

    Cart findAllByUserIdAndProductId(Long id, String productId);

    List<Cart> findAllByUserId(Long user_id);

    void deleteAllByUserId(Long id);
}
