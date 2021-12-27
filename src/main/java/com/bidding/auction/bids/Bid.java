package com.bidding.auction.bids;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.bidding.auction.Transaction.Transaction;
import com.bidding.auction.product.Product;
import com.bidding.auction.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bid {
    @Id
    @GeneratedValue
    private Integer bid_id;
    private Integer bid_price;
    private Date time_placed;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @OneToOne
    private Transaction bidTrans;
    protected Bid(){

    }
    public Bid(Integer bid_id,Integer bid_price,Date time_placed,User user,Product product){
        this.bid_id=bid_id;
        this.bid_price=bid_price;
        this.time_placed=time_placed;
        this.user=user;
        this.product=product;
    }
    public void setUser(User user){
        this.user=user;
    }
    public void setBid_id(Integer bid_id) {
        this.bid_id = bid_id;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public void setBid_price(Integer bid_price) {
        this.bid_price = bid_price;
    }
    public void setTime_placed(Date time_placed) {
        this.time_placed = time_placed;
    }
    public Integer getBid_id() {
        return bid_id;
    }
    public Integer getBid_price() {
        return bid_price;
    }
    public Date getTime_placed() {
        return time_placed;
    }
    public Product getProduct() {
        return product;
    }
    @Override
    public String toString() {
        return String.format("[id:%s,bid-price:%s,time-palced:%s]", bid_id,bid_price,time_placed);
    }
}
