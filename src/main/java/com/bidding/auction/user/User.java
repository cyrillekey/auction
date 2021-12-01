package com.bidding.auction.user;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer userId;
    @NotNull
    private String username;
    @NotNull
    private String email;
    @Size(min = 6)
    private String password;
    @Past
    private Date datejoined;

    User(){

    }
    public User(Integer userId,String username,String email,String passoword,Date datejoined){
        this.userId=userId;
        this.username=username;
        this.email=email;
        this.password=passoword;
        this.datejoined=datejoined;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setDatejoined(Date datejoined) {
        this.datejoined = datejoined;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Date getDatejoined() {
        return datejoined;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public Integer getUserId() {
        return userId;
    }
    public String getUsername() {
        return username;
    }
    @Override
    public String toString() {
        return String.format("[id:%s,email:%s,username:%s,date-joined:%s]",userId,email,username,datejoined);
    }
}
