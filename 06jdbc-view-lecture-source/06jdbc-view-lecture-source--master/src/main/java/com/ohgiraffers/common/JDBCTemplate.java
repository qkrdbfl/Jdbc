package com.ohgiraffers.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

    public static Connection getConnection() {

        Connection con = null;

        Properties prop = new Properties();

        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");

            Class.forName(driver);

            con = DriverManager.getConnection(url, prop);

            //connection은 기본적으로 AutoCommit 설정으로 되어있다
            //해당 설정을 변경해주어야 논리적인 단위로 자바 어플리케이션 내의 트랜잭션 제어가 가능하다
            con.setAutoCommit(false); //오토커밋을 하지 않겠다 라고 설정해줌


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public static void close(Connection con) {

        try {
            if(con != null & !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void close(Statement stmt) {

        try {
            if(stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void close(ResultSet rset) {

        try {
            if(rset != null && !rset.isClosed()) {
                rset.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* 설명. 수동 commit 해본 후 추가할 내용 */
    public static void commit(Connection con) {
        try {
            if(con != null && !con.isClosed()) {
                con.commit(); // 안전하게 커밋을 하고
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection con) {
        try {
            if(con != null && !con.isClosed()) {
                con.rollback(); // 롤백을 할수 있는 코드 구문
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
