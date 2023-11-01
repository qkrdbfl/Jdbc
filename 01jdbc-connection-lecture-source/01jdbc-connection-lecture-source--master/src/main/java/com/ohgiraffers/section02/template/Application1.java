package com.ohgiraffers.section02.template;

import java.sql.Connection;

import static com.ohgiraffers.section02.template.JDBCTemplate.close;
import static com.ohgiraffers.section02.template.JDBCTemplate.getConnection;

public class Application1 {

    public static void main(String[] args) {

        // DB 연결 객체가 필요한 상황에서 JDBCTemplate에 정의한 getConnection 메소드를 호출한다
        Connection con = getConnection();

        // DB 와 연결해서 수행하는 코드
        System.out.println("con = "+ con);

        // DB 연결 객체 반납
        close(con);

    }
}
