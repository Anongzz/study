package com.example.study.service;

import com.example.study.dao.UserDAO;
import com.example.study.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {

    private final UserDAO userDAO;

    public HomeServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public String Account(String userID, String userName, String userPW){
        UserDTO userDTO = UserDTO.builder()
                .userID(userID)
                .userName(userName)
                .userPW(userPW)
                .build();


        if (userDAO.join(userDTO)==-1){
            return "success";
        }else {
            return "fail";
        }
    }

    @Override
    public String Login(String userID, String userPW){
        UserDTO userDTO = UserDTO.builder()
                .userID(userID)
                .userPW(userPW)
                .build();

        if(userDAO.login(userDTO)==1){
            return "success";
        }else {
            return "fail";
        }
    }
}
