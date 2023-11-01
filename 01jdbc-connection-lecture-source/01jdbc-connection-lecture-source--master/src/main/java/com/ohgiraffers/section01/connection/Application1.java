package com.ohgiraffers.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application1 {
    public static void main(String[] args) {

        /* DB 접속을 위한 Connection instance 생성을 위해 레퍼런스 변수를 먼저 선언 */
        Connection con = null;

        try {

            /* 사용할 driver 등록
             *  정확한 클래스명으로 입력 되지 않았을 경우 ClassNotFoundException 오류 발생 */
            Class.forName("oracle.jdbc.driver.OracleDriver");
            /*DriverManager를 이용해 Connection 생성
            * url, user, password 중 올바르지 않은 정보가 입력 되면 접속할수 없으므로 SQLException 발생*/
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","C##EMPLOYEE","EMPLOYEE");

            System.out.println("con : " + con);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(con != null){
                /*DBMS와 연결 된 객체를 반납한다*/
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }
}
