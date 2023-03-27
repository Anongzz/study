package com.example.study.controller;

import com.example.study.service.HomeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class HomeController {

    private final HomeService homeService;
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/")//메인화면
    public String helloJSP(){
        return "index";
    }

    @GetMapping("/join")//회원가입 페이지
    public String joinAccount(){
        return "Join_Account";
    }

    @PostMapping("/account")
    public String accountUser(String userID, String userName, String userPW) {
        String result = homeService.Account(userID,userName,userPW);

        if(result.equalsIgnoreCase("success")){
            return "success";
        }else {
            return "fail";
        }

    }

    @PostMapping("/login")
    public String loginCheckUser(String userID, String userPW, HttpServletRequest request){
        String result = homeService.Login(userID,userPW);

        if(result.equalsIgnoreCase("success")){
            HttpSession session = request.getSession();
            session.setAttribute("id",userID);
            return "userBuildings";
        }else {
            return "fail";
        }
    }



}
