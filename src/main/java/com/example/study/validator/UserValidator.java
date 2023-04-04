package com.example.study.validator;

import com.example.study.dto.UserDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return UserDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO user = (UserDTO) target;
        String userID = user.getUserID();//유저ID
        String checkID;//유저ID 확인용 String
        String userName;//회원가입 시 유저 성명
        String userPW= user.getUserPW();//유저PW
        String userParking;//사용자 주차장 별명

        String basicPrice;//기본요금 int
        String basicTime;//기본시간 int
        String addPrice;//추가요금 int
        String addTime;//추가시간 int
        if (userID == null || userID.trim().isEmpty()) {
            errors.rejectValue("userID", "null or empty id value"); // 문제가 있는 프로퍼티와 에러 메시지를 함께 등록
        }
        if (userPW == null || userPW.trim().isEmpty()) {
            errors.rejectValue("userPW", "null or empty pw value");
        }

    }


}
