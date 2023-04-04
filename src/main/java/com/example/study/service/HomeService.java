package com.example.study.service;

import com.example.study.dto.UserDTO;

public interface HomeService{
    //함수()
    String Account (String userID, String userName, String userPW);

    String Login(String userID, String userPW);

    String UserElementList(String userID, String userParking, String CheckID);

    String EditParking(String userID, String userParking, String basicPrice, String basicTime, String addPrice, String addTime);

    String DeleteParking(String userID,String userParking);

    String CreateParking(String userID, String userParking,String basicPrice, String basicTime, String addPrice, String addTime);
}//interface HomeService
