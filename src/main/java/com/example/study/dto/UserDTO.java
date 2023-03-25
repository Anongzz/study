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

    @Builder
    public UserDTO(String userID, String userName, String userPW) {
        this.userID = userID;
        this.userName = userName;
        this.userPW = userPW;
    }
}
