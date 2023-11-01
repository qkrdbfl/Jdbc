package com.ohgiraffers.section01.tests;

public class Application {
    public static void main(String[] args) {

        //테스트 시나리오
        Calculator calc = new Calculator();

        if (calc != null){
            System.out.println("성공");
        }else {
            System.out.println("실패");
        }

        //sumTwoNumber 메소드가 정상 기능 하는지 테스트
         // 6과 7를 전달하면 합계 13이 되는지 확인
        int result1 = calc.sumTwoNumber(6,7);

        if (result1 == 13){
            System.out.println("6와 7를 전달하여 합계가 13임");
        }else {
            System.out.println("6와 7를 전달하여 합계가 13이 아님");
        }

        //2-1 4와 5를 전달하면 합계 9가 되는지 확인
        int result2 = calc.sumTwoNumber(4,5);

        if (result2 == 9){
            System.out.println("4와 5를 전달하여 합계가 9임");
        }else {
            System.out.println("4와 5를 전달하여 합계가 9가 아님");
        }

        //3. 두 케스트 결과가 모두 통과라면 해당 클래스의 메소드는 신뢰성이 있는 메소드 임을 확인
        if (result1 == 13 && result2 ==9){
            System.out.println("테스트 성공, 나는 감자다");
        }else {
            System.out.println("테스트 실패, 메소드를 다시 확인하세요");
        }
    }
}
