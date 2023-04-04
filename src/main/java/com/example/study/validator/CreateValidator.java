package com.example.study.validator;

import com.example.study.dto.UserDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CreateValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return UserDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO user = (UserDTO) target;
        String userID;//유저ID
        String checkID;//유저ID 확인용 String
        String userName;//회원가입 시 유저 성명
        String userPW;//유저PW
        String userParking=user.getUserParking();//사용자 주차장 별명

        String basicPrice=user.getBasicPrice();//기본요금 int
        String basicTime=user.getBasicTime();//기본시간 int
        String addPrice=user.getAddPrice();//추가요금 int
        String addTime=user.getAddTime();//추가시간 int
        if (userParking == null || userParking.trim().isEmpty()) {
            errors.rejectValue("userParking", "null or empty id value"); // 문제가 있는 프로퍼티와 에러 메시지를 함께 등록
        }
        if (basicPrice == null || basicPrice.trim().isEmpty()) {
            try {
                Integer value = Integer.valueOf(basicPrice);
            }catch (NumberFormatException e){
                errors.rejectValue("basicPrice", "null or empty id value"); // 문제가 있는 프로퍼티와 에러 메시지를 함께 등록
                e.printStackTrace();
            }

        }
        if (basicTime == null || basicTime.trim().isEmpty()) {
            try {
                Integer value = Integer.valueOf(basicPrice);
            }catch (NumberFormatException e){
                errors.rejectValue("basicTime", "null or empty id value"); // 문제가 있는 프로퍼티와 에러 메시지를 함께 등록
                e.printStackTrace();
            }
        }
        if (addPrice == null || addPrice.trim().isEmpty()) {
            try {
                Integer value = Integer.valueOf(basicPrice);
            }catch (NumberFormatException e){
                errors.rejectValue("addPrice", "null or empty id value"); // 문제가 있는 프로퍼티와 에러 메시지를 함께 등록
                e.printStackTrace();
            }
        }
        if (addTime == null || addTime.trim().isEmpty()) {
            try {
                Integer value = Integer.valueOf(basicPrice);
            }catch (NumberFormatException e){
                errors.rejectValue("addTime", "null or empty id value"); // 문제가 있는 프로퍼티와 에러 메시지를 함께 등록
                e.printStackTrace();
            }
        }

    }


}
