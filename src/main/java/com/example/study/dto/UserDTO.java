package com.example.study.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class UserDTO {
    private String userID;
    private String userName;
    private String userPW;

    private String userPWC;//회원가입 시 비밀번호 체크 추가예정

    @Builder
    public UserDTO(String userID, String userName, String userPW) {
        this.userID = userID;
        this.userName = userName;
        this.userPW = userPW;
    }




}
