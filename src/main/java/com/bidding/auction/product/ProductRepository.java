package com.bidding.auction.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{
    @Query(value = "SELECT * from product where pname LIKE %in%" ,nativeQuery = true)
    List<Product> findByPnameContaining(String pname);
}
