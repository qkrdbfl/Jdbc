package com.ohgiraffers.section02.update;

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

public class Application {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("변경할 메뉴 번호를 입력 : ");
        int menuCode = sc.nextInt();
        sc.nextLine();
        System.out.print("변경할 메뉴 이름 입력 : ");
        String menuName = sc.nextLine();
        System.out.print("변경할 메뉴 가격 입력 : ");
        int menuPrice = sc.nextInt();

        MenuDTO changedMenu = new MenuDTO();
        changedMenu.setCode(menuCode);
        changedMenu.setName(menuName);
        changedMenu.setPrice(menuPrice);

        /* --------------------------------------------------------------------- */
        Connection con = getConnection();

        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));

            pstmt = con.prepareStatement(prop.getProperty("updateMenu"));
            pstmt.setString(1, changedMenu.getName());
            pstmt.setInt(2, changedMenu.getPrice());
            pstmt.setInt(3, changedMenu.getCode());

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
            System.out.println("메뉴 변경이 완료 되었습니다.");
        } else {
            System.out.println("메뉴 변경에 실패하였습니다.");
        }
    }
}
