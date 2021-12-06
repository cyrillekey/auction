package com.bidding.auction.bids;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface BidsRepository extends JpaRepository<Bid,Integer>{
    @Query(value = "SELECT * from bid where product_productid=?1 ORDER BY bid_price DESC" ,nativeQuery = true)
    List<Bid> findWinningBid(Integer product_id);
}
