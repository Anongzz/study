package com.example.study.controller;

import com.example.study.dto.UserDTO;
import com.example.study.service.HomeService;
import com.example.study.validator.*;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


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

    @GetMapping("/error")//메인화면
    public String error(){
        return "error";
    }

    @GetMapping("/join")//회원가입 페이지
    public String joinAccount(){
        return "Join_Account";
    }

    @GetMapping("/userBuildings")
    public String userBuildings(){ return "userBuildings";}

    @GetMapping("/createParking")
    public String createParking(){return "CreateParking";}

    @GetMapping("/editParking/{PName}")
    public String editParking(@PathVariable String PName, HttpServletRequest request){
        HttpSession session = request.getSession();

        session.setAttribute("buildingName",PName);

        return "editParking";
    }//editParking()

    @PostMapping("/account")//회원가입 데이터 전송 및 추가
    public String accountUser(UserDTO userDTO, BindingResult bindingResult) { // 요청 파라미터 빈 값 처리 -> NULL, 기본값을 설정하거나, wrapper 클래스로 처리
        AccountValidator accountValidator = new AccountValidator();
        accountValidator.validate(userDTO,bindingResult);

        if(bindingResult.hasErrors()){
            return "fail";
        }else{
            String userID = userDTO.getUserID();
            String userName = userDTO.getUserName();
            String userPW = userDTO.getUserPW();

            String result = homeService.Account(userID,userName,userPW);

            if(result.equalsIgnoreCase("success")){
                return "redirect:/";
            }else {
                return "fail";
            }
        }



    }//accountUser()

    /*
    // @ModelAttribute 예제 설명용
    @PostMapping("/account")//회원가입 데이터 전송 및 추가
    public String accountUser2(UserDTO userDTO) { // 기본 String, int, Integer 는 요청 파라미터를 쓰면 자동으로 @RequestParam, 하지만 커스텀 클래스는 자동으로 @ModelAttribute
        String result = homeService.Account(userID,userName,userPW);

        if(result.equalsIgnoreCase("success")){
            return "success";
        }else {
            return "fail";
        }

    }//accountUser()
    */

    /*
    @PostMapping("/account")//회원가입 데이터 전송 및 추가
    @ResponseBody
    public String accountUser2(@RequestBody String messageBody) { // 기본 String, int, Integer 는 요청 파라미터를 쓰면 자동으로 @RequestParam, 하지만 커스텀 클래스는 자동으로 @ModelAttribute

        String result = homeService.Account(userID,userName,userPW);

        if(result.equalsIgnoreCase("success")){
            return "success";
        }else {
            return "fail";
        }

        return "--JSON 문자열--";
    }//accountUser()
    */

    /**기존 로그인 처리*/
//    @PostMapping("/login")//로그인 | 로그인 성공시 세션에 유저ID 저장
//    public String loginCheckUser(String userID, String userPW, HttpServletRequest request) {
//        String result = homeService.Login(userID,userPW);
//
//        if(result.equalsIgnoreCase("success")){
//            HttpSession session = request.getSession();
//            session.setAttribute("id",userID);
//            return "redirect:/userBuildings";
//        }else {
//            return "fail";
//        }
//    }//loginCheckUser()

    /**Validator를 사용한 요청 파라미터 값 검사*/
    @PostMapping("/login")//로그인 | 로그인 성공시 세션에 유저ID 저장
    public String loginCheckUser2(UserDTO userDTO, HttpServletRequest request, BindingResult result) {
        UserValidator userValidator = new UserValidator();
        userValidator.validate(userDTO,result);

        if (result.hasErrors()){
            return "fail";
        }else {
            String setResult = homeService.Login(userDTO.getUserID(),userDTO.getUserPW());
            if(setResult.equalsIgnoreCase("success")){
                HttpSession session = request.getSession();
                session.setAttribute("id",userDTO.getUserID());
                return "redirect:/userBuildings";
            }else {
                return "fail";
            }
        }

    }//loginCheckUser()
    /**기존 유저 고유 데이터 조회*/
    @GetMapping("/parking/{userID}/{PName}")//유저 고유 데이터 조회 | 받아온 userID와 세션의 유저ID를 대조
    public String ParkingList(@PathVariable String PName, @PathVariable String userID, HttpServletRequest request){

        HttpSession session = request.getSession();
        String CheckID = (String) session.getAttribute("id");
        String result = homeService.UserElementList(userID,PName,CheckID);


        if(result.equalsIgnoreCase("success")){
            session.setAttribute("buildingName",PName);
            return "userIndependentParking";
        }else {
            return "fail";
        }
    }// ParkingList()

    /**기존 데이터 수정*/
//    @PostMapping("/edit")//유저 고유 데이터 수정
//    public String EditList(String basicPrice, String basicTime, String addPrice, String addTime, HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        String userID = (String)session.getAttribute("id");
//        String PName = (String)session.getAttribute("buildingName");
//
//
//        String result = homeService.EditParking(userID,PName,basicPrice,basicTime,addPrice,addTime);
//
//        if(result.equalsIgnoreCase("success")){
//            return "redirect:/userBuildings";
//        }else {
//            return "fail";
//        }
//
//    }//EditList()

    @PostMapping("/edit")//유저 고유 데이터 수정
    public String EditList(UserDTO userDTO, HttpServletRequest request,BindingResult bindingResult) {

        EditValidator editValidator = new EditValidator();
        editValidator.validate(userDTO,bindingResult);

        if(bindingResult.hasErrors()){
            return "fail";
        }else{
            HttpSession session = request.getSession();
            String userID = (String)session.getAttribute("id");
            String PName = (String)session.getAttribute("buildingName");


            String basicPrice=userDTO.getBasicPrice();
            String basicTime = userDTO.getBasicTime();
            String addPrice = userDTO.getAddPrice();
            String addTime = userDTO.getAddTime();
            String result = homeService.EditParking(userID,PName,basicPrice,basicTime,addPrice,addTime);

            if(result.equalsIgnoreCase("success")){
                return "redirect:/userBuildings";
            }else {
                return "fail";
            }
        }



    }//EditList()

    @GetMapping("/delete/{PName}")
    public String DeleteList(HttpServletRequest request,@PathVariable String PName){
        HttpSession session = request.getSession();
        String userID = (String)session.getAttribute("id");
        System.out.println("userid: "+userID);
        System.out.println("Pname: "+PName);

        String result = homeService.DeleteParking(userID,PName);

        if(result.equalsIgnoreCase("success")){
            return "redirect:/userBuildings";
        }else {
            return "fail";
        }
    }//DeleteList()

    @PostMapping("/create")
    public String createList(UserDTO userDTO, HttpServletRequest request,BindingResult bindingResult){

        CreateValidator createValidator = new CreateValidator();
        createValidator.validate(userDTO,bindingResult);

        if (bindingResult.hasErrors()){
            return "fail";
        }else{
            String userParking = userDTO.getUserParking();
            String basicPrice = userDTO.getBasicPrice();
            String basicTime = userDTO.getBasicTime();
            String addPrice = userDTO.getAddPrice();
            String addTime = userDTO.getAddTime();

            HttpSession session = request.getSession();
            String userID = (String) session.getAttribute("id");

            String result = homeService.CreateParking(userID, userParking, basicPrice, basicTime, addPrice, addTime);

            if(result.equalsIgnoreCase("success")){
                return "redirect:/userBuildings";
            }else {
                return "fail";
            }
        }


    }





}//class HomeController
