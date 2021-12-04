package com.bidding.auction.user;

public class LoginDetails {
    private String username;
    private String password;

    public LoginDetails(String username,String password){
        this.username=username;
        this.password=password;
    }
    public String getPassword(){
        return password;
    }
    public String getUsername() {
        return username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String toString() {
        return String.format("[password:%s,username:%s]", password,username);
    }
}
