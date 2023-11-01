package com.ohgiraffers.section02.run;

import com.ohgiraffers.section02.servuce.model.service.MenuService;

public class Application {

    public static void main(String[] args) {

        // Servuce의 역할
        /*1. Connection 생성
        * 2. DAO의 메소드 호출
        * 3. 트랜잭션 제어
        * 4. Connection 닫기
        * */
        new MenuService().registNewMenu();

    }
}
