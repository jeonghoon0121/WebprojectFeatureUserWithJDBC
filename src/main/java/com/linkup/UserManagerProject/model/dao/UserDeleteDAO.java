package com.linkup.UserManagerProject.model.dao;

import com.linkup.UserManagerProject.model.dto.UserDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class UserDeleteDAO {
    private Properties prop = new Properties();
    String addr = "C:\\lecture2\\JDBC\\WebprojectFeatureUserWithJDBC\\src\\main\\java\\com\\linkup\\UserManagerProject\\mapper\\user-query.xml";

    public void deleteUser(Connection con, UserDTO userDTO2) {
        PreparedStatement pstmt=null;
        try {
            prop.loadFromXML(new FileInputStream(addr));
            String query =prop.getProperty("DeleteUser");
            pstmt=con.prepareStatement(query);
            pstmt.setInt(1, userDTO2.getUserCode());
            int result=pstmt.executeUpdate();
            System.out.println(result);
            if(result==1){
                System.out.println("성공");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
