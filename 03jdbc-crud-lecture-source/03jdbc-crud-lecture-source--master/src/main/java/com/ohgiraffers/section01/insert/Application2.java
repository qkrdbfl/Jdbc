package com.ohgiraffers.section01.insert;

import com.ohgiraffers.model.dto.MenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {
    public static void main(String[] args) {
        /* 다른 클래스에서 작성 된 내용으로 가정
        * Scanner 객체를 이용해 입력 받는 값으로 insert */
        Scanner sc = new Scanner(System.in);
        System.out.print("메뉴 이름 입력 : ");
        String menuName = sc.nextLine();
        System.out.print("메뉴 가격 입력 : ");
        int menuPrice = sc.nextInt();
        System.out.print("카테고리 코드 입력 : ");
        int categoryCode = sc.nextInt();
        sc.nextLine();
        System.out.print("판매여부 결정(Y/N) : ");
        String orderableStatus = sc.nextLine().toUpperCase();

        /* 값을 뭉쳐서 보내기 위해 MenuDTO를 작성한 뒤 담는다. */
        MenuDTO newMenu = new MenuDTO();
        newMenu.setName(menuName);
        newMenu.setPrice(menuPrice);
        newMenu.setCategoryCode(categoryCode);
        newMenu.setOrderableStatus(orderableStatus);

        /* -------------------------------------------------------------------------- */
        Connection con = getConnection();

        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
            String query = prop.getProperty("insertMenu");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, newMenu.getName());
            pstmt.setInt(2, newMenu.getPrice());
            pstmt.setInt(3, newMenu.getCategoryCode());
            pstmt.setString(4, newMenu.getOrderableStatus());

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }

        if(result > 0) {
            System.out.println("메뉴 등록이 완료 되었습니다.");
        } else {
            System.out.println("메뉴 등록이 실패 하였습니다.");
        }

    }
}
