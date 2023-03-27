package com.example.study.dao;

import com.example.study.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

@Component
public class UserDAO {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.name}")
    private String name;

    @Value("${spring.datasource.password}")
    private String password;

    public int join(UserDTO userDTO){
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(url,name,password);
            String sql = "INSERT INTO parkinguser VALUES (?,?,HEX(AES_ENCRYPT(?,'ABC')))";

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
    }

    public int login(UserDTO userDTO){

        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL = "SELECT userID, AES_DECRYPT(UNHEX(userPW), 'ABC')  from parkinguser where userID = ? and ? = (SELECT cast(AES_DECRYPT(UNHEX(userPW), 'ABC') as char(100)) FROM parkinguser where userID=?);";

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
                getPW = rs.getString("AES_DECRYPT(UNHEX(userPW), 'ABC')");
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
    }





}
