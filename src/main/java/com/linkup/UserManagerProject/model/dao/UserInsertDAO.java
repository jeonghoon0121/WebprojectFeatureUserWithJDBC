package com.linkup.UserManagerProject.model.dao;

import com.linkup.UserManagerProject.model.dto.UserDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import static com.linkup.UserManagerProject.common.JDBCTemplate.close;

public class UserInsertDAO {
    private Properties prop = new Properties();
    String addr = "C:\\lecture2\\JDBC\\WebprojectFeatureUserWithJDBC\\src\\main\\java\\com\\linkup\\UserManagerProject\\mapper\\user-query.xml";

    public void selectAllCode(Connection con) {

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        List<UserDTO> userDTOList = new ArrayList<>();

        int maxMenuCode = 1;

        try {
            prop.loadFromXML(new FileInputStream(addr));
            String query = prop.getProperty("selectUser");
//            System.out.println("query = " + query);
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                UserDTO userDTO = new UserDTO();
                userDTO.setUserCode(rset.getInt("USER_CD"));
                userDTO.setUserID(rset.getString("USER_ID"));
                userDTO.setUserPassword(rset.getString("USER_PW"));

                System.out.println(userDTO.getUserCode());
                System.out.println(userDTO.getUserID());
                System.out.println(userDTO.getUserPassword());
                userDTOList.add(userDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvalidPropertiesFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(rset);
        }
    }

    public int selectLastCode(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        try {
            prop.loadFromXML(new FileInputStream(addr));
            String query = prop.getProperty("selectUser2");
//            System.out.println("query = " + query);
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();
            if (rset.next()) {
                UserDTO userDTO = new UserDTO();
                userDTO.setUserCode(rset.getInt("USER_CD"));
                userDTO.setUserID(rset.getString("USER_ID"));
                userDTO.setUserPassword(rset.getString("USER_PW"));
                System.out.println(userDTO.getUserCode());
                System.out.println(userDTO.getUserID());
                System.out.println(userDTO.getUserPassword());
                return (userDTO.getUserCode() + 1);
            }
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvalidPropertiesFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);

        }

    }

    public void insertNewUser(Connection con,UserDTO userDTO) {
        int lastnumber=selectLastCode(con);
        System.out.println(lastnumber);
        userDTO.setUserCode(lastnumber);
        PreparedStatement pstmt = null;
        try {
            prop.loadFromXML(new FileInputStream(addr));
            String query = prop.getProperty("InsertNewUser");
            pstmt=con.prepareStatement(query);
            pstmt.setInt(1,userDTO.getUserCode());
            pstmt.setString(2,userDTO.getUserID());
            pstmt.setString(3,userDTO.getUserPassword());
            int result=pstmt.executeUpdate();
            System.out.println(result);
            if(result==1){
                System.out.println("성공");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
        }
    }
}
