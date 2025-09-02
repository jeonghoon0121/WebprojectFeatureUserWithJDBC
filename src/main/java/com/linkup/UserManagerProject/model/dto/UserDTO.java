package com.linkup.UserManagerProject.model.dto;

public class UserDTO {
    private int userCode;
    public String userID;
    private String userPassword;
    public UserDTO() {}

    public UserDTO(int userCode, String userID, String userPassword) {
        this.userCode = userCode;
        this.userID = userID;
        this.userPassword = userPassword;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userCode=" + userCode +
                ", userID='" + userID + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
