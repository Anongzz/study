package com.example.study.dao;

import com.example.study.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            String sql = "INSERT INTO parkinguser values(?,?,?)";

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
}
