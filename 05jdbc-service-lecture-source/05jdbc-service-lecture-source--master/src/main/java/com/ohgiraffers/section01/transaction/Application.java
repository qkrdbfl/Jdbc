package com.ohgiraffers.section01.transaction;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application {

    public static void main(String[] args) {

        Connection conn = getConnection();

        /* COMMIT 상태 확인 */
        try {
            System.out.println("AutoCommit의 현재 설정 값 : " + conn.getAutoCommit());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        int result1 = 0;
        int result2 = 0;

        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
            String query1 = prop.getProperty("insertCategory");
            String query2 = prop.getProperty("insertMenu");

            /*1. TBL_CATEGORY 테이블에 1행 삽입 */
            pstmt1 = conn.prepareStatement(query1);
            pstmt1.setString(1, "테스트카테고리");
            pstmt1.setInt(2, 1);

            result1 = pstmt1.executeUpdate(); //삽입 함 성공. 이 동작이 끝나면 자동으로 커밋까지 되도록 되어있
            // 하지만 DTO클래스에서 오토커밋을 하지 않는다는 코드를 씀으로 커밋을 하지 않고 밑의 구문까지 읽게 됐다

            /*2. TBL_MENU 테이블에 1행 삽입 */
            pstmt2 = conn.prepareStatement(query2);
            pstmt2.setString(1, "테스트메뉴");
            pstmt2.setInt(2, 10000);
            /* TBL_CATEGORY 에 존재하지 않는 CATEGORY_CODE를 삽입하려고 하면
             * 부모 키를 찾지 못하는 외래키 제약조건 위반 오류가 발생한다. */
            pstmt2.setInt(3, 0); // 0은 카테고리에 없는 숫자라 실패
            pstmt2.setString(4, "Y");

            result2 = pstmt2.executeUpdate(); //외래키 사용 실패예상.

            System.out.println("result1 : " + result1);
            System.out.println("result2 : " + result2);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt1);
            close(pstmt2);

            /* 트랜잭션(논리적인 기능 수행 단위) 관리를 위해 2개의 insert가 모두 잘 동작했는지 판단하여
             * 잘 동작했을경우 commit, 둘 중 하나라도 잘 동작하지 않았을 경우 rollback을 수행한다. */
            if(result1 > 0 && result2 > 0) {
                commit(conn);
            } else {
                rollback(conn);
            }

            close(conn);
        }









    }


}
