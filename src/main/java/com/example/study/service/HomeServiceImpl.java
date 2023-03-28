package com.example.study.service;

import com.example.study.dao.UserDAO;
import com.example.study.dto.UserDTO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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
    }//Account()

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
    }//Login()

    @Override
    public String UserElementList(String userID, String userParking,String checkID){
        UserDTO userDTO = UserDTO.builder()
                .userID(userID)
                .userParking(userParking)
                .checkID(checkID)
                .build();

        if(userDAO.GetParkingList(userDTO)==1){
            return "success";
        }else {
            return "fail";
        }

    }//UserElementList()
}//class HomeServiceImpl
