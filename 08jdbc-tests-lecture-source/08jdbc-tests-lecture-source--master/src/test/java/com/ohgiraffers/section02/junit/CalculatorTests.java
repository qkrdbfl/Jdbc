package com.ohgiraffers.section02.junit;

//단위 테스트(Unit Test) //작은 테스트
// 한가지 기능(함수)마다 일을 잘 수행 하는지 확인하며 특정 모듈이 의도된 대로 정확히 작동하는지 검증하는 정차
// 연관 컴포넌트가 개발되지 않더라도 기능별 개발이 완료 된 것을 증명할수 있음.
import com.ohgiraffers.section01.tests.Calculator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test; //jupiter 인지 확인 코끼리에 써진 라이브러리에서 가져오는건지 확인

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTests {
// 썸투넘버라는 테스트
    private static Calculator calc; // static 를 써야함 왜냐? @BeforeAll,@AfterAll 메소드는 static를 써야하는 조건이 있음

    //1. Calculator 인스턴스 생성이 잘 되는지 테스트
    @BeforeAll //테스트 하기전에 딱 한번만 동작함
    //@BeforeEach :
    public static void setUp(){ // setUp : 준비하는 동작
        System.out.println("Calculator 인스턴스 생성");
        calc = new Calculator();
    }

    //2. SumTwoNumber 메소드가 정상 기능하는지 테스트
    @Test //테스트라는 어노테이션
    public void testSumTwoNumber_4와_5를_전달하면_합계가_9가_되는지_확인(){
        System.out.println("2-1 테스트 동작");
        int result = calc.sumTwoNumber(4,5);
        //해당값이 동일한지 확인하기 위해 assertEquals() 메소드를 써본다
        assertEquals(9,result);

    }
    @Test
    public void testSumTowNumber_6과_7을_전달하면_합계가_13이_되는지_확인(){
        System.out.println("2-2 테스트 동작");
        int result = calc.sumTwoNumber(6,7);

        assertEquals(12,result,1); //델타 : 의미는 얘가 가지고 있는 예상값에서 입력 값(+1 ~ -1)정도는 보정하겠다 의 의미
        // 델타 1을 지우면  필요 : 12  실제: 13 이라는 에러가 남 6+7은 13인데 12라고만 정의해놨기 때문
    }

    //위의 테스트 결과가 모두 통과하면 해당 클래스의 메소드는 신뢰성 있는 메소드임을 확인
    //스탯틱 메소드로 선언되어야 한다는 제한이 있음 안쓰면 에러
    @AfterAll // : 테스트들이 모두 다 끝나고 실행하는 메소드
    public static void afterTest(){
        System.out.println("테스트 완료");
    }

}
