package state.hrsystem;

import state.hrsystem.context.Employee;

import java.time.LocalDateTime;

/**
 * HR 시스템 상태 패턴 연습문제 테스트 클래스
 * 구현 완료 후 이 클래스를 실행하여 테스트해보세요.
 */
public class HRSystemTest {
    
    public static void main(String[] args) {
        System.out.println("=== HR 시스템 상태 패턴 연습문제 테스트 ===\n");
        Employee employee = new Employee();

        System.out.println(employee.toString());
        employee.markStartWork();
        employee.takeBreak();
        employee.markStartWork();
        employee.startLeave();
        employee.returnFromLeave();
        System.out.println(employee.toString());

    }
} 