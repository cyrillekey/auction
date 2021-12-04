package com.bidding.auction.bundle;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;

import com.bidding.auction.events.Event;
import com.bidding.auction.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
public class Bundle {
    @Id
    @GeneratedValue
    private Integer bundleId;
    @Past
    private Date bundleCreation;
    @Future
    private Date bundleExpiry;
    private String image_url;
    @OneToMany(mappedBy = "bundleProduct")
    private List<Product> products;
    private Float bundlePrice;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Event oneEvent;
    protected Bundle(){

    }
    public Bundle(Integer bundleId,Date bundleCreation,Date bundleExpiry,String image_url,Float bundlePrice){
        this.bundleId=bundleId;
        this.bundleCreation=bundleCreation;
        this.bundleExpiry=bundleExpiry;
        this.bundlePrice=bundlePrice;
        this.image_url=image_url;
    }
    public void setBundleCreation(Date bundleCreation) {
        this.bundleCreation = bundleCreation;
    }
    public void setBundleExpiry(Date bundleExpiry) {
        this.bundleExpiry = bundleExpiry;
    }
    public void setBundleId(Integer bundleId) {
        this.bundleId = bundleId;
    }
    public void setBundlePrice(Float bundlePrice) {
        this.bundlePrice = bundlePrice;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public Date getBundleCreation() {
        return bundleCreation;
    }
    public Date getBundleExpiry() {
        return bundleExpiry;
    }
    public Integer getBundleId() {
        return bundleId;
    }
    public Float getBundlePrice() {
        return bundlePrice;
    }
    public String getImage_url() {
        return image_url;
    }
    public Event getOneEvent() {
        return oneEvent;
    }
    public void setOneEvent(Event oneEvent) {
        this.oneEvent = oneEvent;
    }
    public List<Product> getProducts() {
        return products;
    }
    
}
