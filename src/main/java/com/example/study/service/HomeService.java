package com.example.study.service;

public interface HomeService{
    //함수()
    String Account (String userID, String userName, String userPW);

    String Login(String userID, String userPW);

    String UserElementList(String userID, String userParking, String CheckID);

    String EditParking(String userID, String userParking, String basicPrice, String basicTime, String addPrice, String addTime);
}//interface HomeService
