package com.ohgiraffers.section01.view;

import com.ohgiraffers.section01.model.dto.OrderDTO;
import com.ohgiraffers.section01.model.service.OrderService;
import com.ohgiraffers.section01.servuce.model.dto.CategoryDTO;
import com.ohgiraffers.section01.servuce.model.dto.MenuDTO;
import com.ohgiraffers.section01.servuce.model.dto.OrderMenuDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OrderMenu {

    private OrderService orderService = new OrderService();

    public void displayMainMenu() {
        //--- 반복 -------------
        /* 1. 카테고리 조회
         * 2. 해당 카테고리의 메뉴 조회
         * 3. 사용자에게 어떤 메뉴를 주문 받을 건지 입력
         * 4. 주문할 수량 입력
         *------------------------
         * 5. 주문
         * */

        List<OrderMenuDTO> orderMenuList = new ArrayList<>();
        int totalOrderPrice = 0; //총금액

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("============ 음식 주문 프로그램 ===========");

            //1. 카테고리 조회 후 출력
            List<CategoryDTO> categoryList = orderService.selectAllCategory();
            for (CategoryDTO category : categoryList) {
                System.out.println(category.getName());
            }

            System.out.println(" ========================================");
            System.out.println("주문하실 카테고리를 선택해 주세요 : ");
            String inputCategory = sc.nextLine();

            int categoryCode = 0;
            for (CategoryDTO category : categoryList) {
                if (category.getName().equals(inputCategory)) {
                    categoryCode = category.getCode();
                }
            }

            System.out.println("=============== 주문 가능 메뉴 ===============");
            List<MenuDTO> menuList = orderService.selectMenuByCategoryCode(categoryCode);
            for (MenuDTO menu : menuList) {
                System.out.println(menu.getName() + " : " + menu.getPrice() + "원");
            }

            System.out.println("주문 하실 메뉴를 선택하세요 : ");
            String inputMenu = sc.nextLine();

            int menuCode = 0;
            int menuPrice = 0;
            for (MenuDTO menu : menuList){
                if (menu.getName().equals(inputMenu)){
                    menuCode = menu.getCode();
                    menuPrice = menu.getPrice();
                }
            }
            System.out.println("주문하실 수량을 입력하세요 : ");
            int orderAmount = sc.nextInt();

            OrderMenuDTO orderMenu = new OrderMenuDTO();
            orderMenu.setMenuCode(menuCode);
            orderMenu.setOrderAmount(orderAmount);

            orderMenuList.add(orderMenu);
            totalOrderPrice += (menuPrice * orderAmount); // 금액 누적연산

            System.out.println("계속 주문 하시겠습니까? (예/ 아니오) : ");
            sc.nextLine(); // 버퍼 지우기

            boolean isContinue = sc.nextLine().equals("예");

            if (!isContinue) break; //펄스면 빠져나감

        } while (true);

        System.out.println("================== 주문 목록 확인 =================");
        for (OrderMenuDTO orderMenu : orderMenuList ){
            System.out.println(orderMenu);
        }

        Date orderTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String date = dateFormat.format(orderTime); // 받아서 문자열로 바꿔줌
        String time = timeFormat.format(orderTime); // 받아서 문자열로 바꿔줌

        OrderDTO order = new OrderDTO();
        order.setTime(time);
        order.setDate(date);
        order.setTotalOrderPrice(totalOrderPrice); // 반복문을 돌면서 합산한거
        order.setOrderMenuList(orderMenuList); // 그때마다 오더메뉴 받은거

        //주문 내역을 전달하여 데이터베이스에 저장
        int result = orderService.registOrder(order);

        if (result>0){
            System.out.println("주문이 완료");
        }else {
            System.out.println("주문이 실패");
        }
    }
}
