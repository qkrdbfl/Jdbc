package com.ohgiraffers.section02.template;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {

    public  static Connection getConnection(){

        Connection con = null;
        Properties prop = new Properties();

        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");

            Class.forName(driver);

            con = DriverManager.getConnection(url,prop);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        return con;
    }

    // DB 연결 객체 반납시 호출할 메소드
    public static void  close(Connection con){

        try {
            // Connection 객체가 존재하면서 닫히지 않았을때
            if (con != null && !con.isClosed()){
                con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
