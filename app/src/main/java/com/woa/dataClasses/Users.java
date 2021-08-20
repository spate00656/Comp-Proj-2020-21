package com.woa.dataClasses;

public class Users {
    String userAddress,userEmail,userId,userName,userPhoneNo,image;

    public Users() {
    }

    public Users(String userAddress, String userEmail, String userId, String userName, String userPhoneNo, String image) {
        this.userAddress = userAddress;
        this.userEmail = userEmail;
        this.userId = userId;
        this.userName = userName;
        this.userPhoneNo = userPhoneNo;
        this.image = image;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhoneNo() {
        return userPhoneNo;
    }

    public String getImage() {
        return image;
    }
}
