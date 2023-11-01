package com.ohgiraffers.section02.servuce.model.dao;

import com.ohgiraffers.section02.servuce.model.dto.CategoryDTO;
import com.ohgiraffers.section02.servuce.model.dto.MenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;

public class MenuDAO {

    private Properties prop = new Properties();

    public MenuDAO() {
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //신규 카테고리 등록용 메소드
    public int insertNewCategory(Connection con, CategoryDTO newCategory) {

        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("insertCategory");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, newCategory.getName());
            pstmt.setObject(2, newCategory.getRefCategoryCode());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;
    }

    //카테고리 테이블에 삽입 될때 발생한 카테고리 코드 조회 메소드
    public int selectLestCategoryCode(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("getCurrentSequence");
        int newCategoryCode = 0;

        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();

            if (rset.next()){
                newCategoryCode = rset.getInt("CURRVAL");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return newCategoryCode;
    }

    //신규 메뉴 등록용 메소드
    public int insertNewMenu(Connection con, MenuDTO newMenu) {

        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("insertMenu");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, newMenu.getName());
            pstmt.setInt(2, newMenu.getPrice());
            pstmt.setInt(3, newMenu.getCategoryCode());
            pstmt.setString(4, newMenu.getOrderableStatus());

            result = pstmt.executeUpdate(); //실행하는 구문

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt); //꼭 닫아주기
        }

        return result;
    }

}
