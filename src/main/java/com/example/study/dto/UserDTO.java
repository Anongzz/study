package com.example.study.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class UserDTO {
    private String userID;//유저ID
    private String checkID;//유저ID 확인용 String
    private String userName;//회원가입 시 유저 성명
    private String userPW;//유저PW
    private String userParking;//사용자 주차장 별명
    private String userPWC;//회원가입 시 비밀번호 체크 추가예정

    private String basicPrice;//기본요금
    private String basicTime;//기본시간
    private String addPrice;//추가요금
    private String addTime;//추가시간


    @Builder
    public UserDTO(String userID,String checkID, String userName, String userPW, String userParking,String basicPrice, String basicTime, String addPrice, String addTime) {
        this.userID = userID;
        this.checkID = checkID;
        this.userName = userName;
        this.userPW = userPW;
        this.userParking=userParking;
        this.basicPrice = basicPrice;
        this.basicTime = basicTime;
        this.addPrice = addPrice;
        this.addTime = addTime;
    }//UserDTO()




}//class UserDTO
