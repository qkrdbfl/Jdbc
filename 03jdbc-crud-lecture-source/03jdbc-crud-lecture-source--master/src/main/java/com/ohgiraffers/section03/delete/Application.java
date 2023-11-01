package com.ohgiraffers.section03.delete;

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
        Connection con = getConnection();

        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
            String query = prop.getProperty("deleteMenu");

            Scanner sc = new Scanner(System.in);
            System.out.print("삭제할 메뉴 번호를 입력 : ");
            int menuCode = sc.nextInt();

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, menuCode);

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
            System.out.println("해당 메뉴를 삭제 완료했습니다.");
        } else {
            System.out.println("해당 메뉴가 없어 삭제 불가합니다.");
        }
    }
}
