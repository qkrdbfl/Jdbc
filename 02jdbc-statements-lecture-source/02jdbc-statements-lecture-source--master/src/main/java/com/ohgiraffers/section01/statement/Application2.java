package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {

    public static void main(String[] args) {
        //1. Connection 생성
        Connection con = getConnection();
        //2. Statement 선언
        Statement stmt = null;
        //3. ResultSet 선언( )
        ResultSet rset = null;

        try {
            //4. Statement 객체를 Connection 객체를 이용해 생성
            stmt = con.createStatement();

            Scanner sc = new Scanner(System.in);
            System.out.println("조회하려는 사번을 입력해 주세요 : ");
            String empId = sc.nextLine();
            String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = '" + empId + "'";

            //5. executeQuery() 메소드로 쿼리문 실행후 결과 ResultSet 으로 반환
            rset = stmt.executeQuery(query);

            //6. ResultSet에 담긴 결과물을 컬럼 이름을 이용해서 꺼내서 출력
            if (rset.next()){
                System.out.println(rset.getString("EMP_ID")+ ", "+ rset.getString("EMP_NAME"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //7. 사용한 자원 반납
            close(rset);
            close(stmt);
            close(con);
        }
    }
}
