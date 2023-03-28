package com.example.study.service;

public interface HomeService{
    //함수()
    String Account (String userID, String userName, String userPW);

    String Login(String userID, String userPW);

    String UserElementList(String userID, String parkingName, String CheckID);
}
