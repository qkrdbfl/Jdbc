package com.ohgiraffers.section03.layertests.model.dao;

import com.ohgiraffers.section03.layertests.model.dto.CategoryDTO;
import com.ohgiraffers.section03.layertests.model.dto.OrderDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderDAOTests {
    // testSelectAllCategory 메소드 테스트

    private static OrderDAO orderDAO; // OrderDAO 활용

    private static Connection con; // Connection 활용 OrderDAO클래스에 쓰인 testSelectAllCategory() 메소드 안에 Connection를 참조 하고 있기 때문에 여기서도 써줘야함
    private static OrderDTO order; // 위에 셋팅해놓고

    //    @BeforeAll
//    public static void setUp(){ //실질적인 객체
//        orderDAO = new OrderDAO();
//        con = getConnection();
//    }

    @BeforeAll
    public static void setUp() { //실질적인 객체
        orderDAO = new OrderDAO();
        con = getConnection();

        //testInsertOrder 메소드 테스트를 위해 밑의 코드 추가
        order = new OrderDTO();
        order.setDate("20/12/31");
        order.setTime("12:25:41");
        order.setTotalOrderPrice(35000);
    }


    @Test //테스트를 한다라는 뜻
    // OrderDAO클래스에 public List<CategoryDTO> selectAllCategory(Connection con)
    public void testSelectAllCategory(){ //()안에 참조 하고 있는것 테스트 전인 위에 미리 셋팅해두기
        List<CategoryDTO>categoryList = orderDAO.selectAllCategory(con);
        assertNotNull(categoryList); //categoryList 에 전달된 인스턴스가 nill이 안들어가면 성공

        categoryList.forEach(System.out::println); // 출력 해봐서 확인해보기
    }


    //삽입하는 테스트도 해보기
    @Test
    //OrderDAO클래스에  public static int insertOrder(Connection con, OrderDTO order) 라고 되어있음
    public void testInsertOrder(){

        int result = OrderDAO.insertOrder(con,order); // insertOrder는 2개 참조 하고 있음 테스트 전에 셋팅하기

        assertEquals(1,result);
    }




}
