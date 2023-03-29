package com.example.study.dao;

import com.example.study.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.sql.*;

@Component
public class UserDAO {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.name}")
    private String name;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${mysql.password.code}")
    private String aesCode;

    public int join(UserDTO userDTO){//회원가입 정보 추가 쿼리 전송
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(url,name,password);
            String sql = "INSERT INTO parkinguser VALUES (?,?,HEX(AES_ENCRYPT(?,aesCode)))";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userDTO.getUserName());
            pstmt.setString(2, userDTO.getUserID());
            pstmt.setString(3, userDTO.getUserPW());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return -1;
    } //join()

    public int login(UserDTO userDTO){//로그인시 입력값과 서버 데이터 대조

        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL = "SELECT userID, AES_DECRYPT(UNHEX(userPW), '"+aesCode+"')  from parkinguser where userID = ? and ? = (SELECT cast(AES_DECRYPT(UNHEX(userPW),'"+aesCode+"' ) as char(100)) FROM parkinguser where userID=?);";
        String getID = "";

        String getPW = "";

        try {
            conn = DriverManager.getConnection(url,name,password);
            pstmt = conn.prepareStatement(SQL);

            String userID = userDTO.getUserID();
            String userPW = userDTO.getUserPW();
            String CheckID = userDTO.getUserID();

            // 값 보내기
            pstmt.setString(1, userID);
            pstmt.setString(2, userPW);
            pstmt.setString(3, CheckID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                // 값 받기
                getID = rs.getString("userID");
                getPW = rs.getString("AES_DECRYPT(UNHEX(userPW), '"+aesCode+"')");
            }
            rs.close();
            if (getID.equals(userID) && getPW.equals(userPW)) {//로그인 성공

                return 1;
            } else {//로그인 실패
                return 2;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 2;
    } //login()

    public int GetParkingList(UserDTO userDTO){//유저 고유데이터 페이지 진입 시 세션값이 유효한지 조회
        String userID = userDTO.getUserID();
        String checkID = userDTO.getCheckID();

        if(userID.equals(checkID)){
            return 1;
        }else {
            return 2;
        }


    } //GetParkingList()

    public int edit(UserDTO userDTO){//회원가입 정보 추가 쿼리 전송
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(url,name,password);
            String sql = "update parkingprice set basicPrice=?,basicTime=?,addPrice=?,addTime=? where userID=? and buildingName=?;";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, Integer.parseInt(userDTO.getBasicPrice()));
            pstmt.setInt(2, Integer.parseInt(userDTO.getBasicTime()));
            pstmt.setInt(3, Integer.parseInt(userDTO.getAddPrice()));
            pstmt.setInt(4, Integer.parseInt(userDTO.getAddTime()));
            pstmt.setString(5, userDTO.getUserID());
            pstmt.setString(6, userDTO.getUserParking());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 1;
    } //edit()





}//class UserDAO
