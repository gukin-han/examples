package state.vendingmachine;

/**
 * 상태 패턴 연습문제 테스트 클래스
 * 구현 완료 후 이 클래스를 실행하여 테스트해보세요.
 */
public class ExerciseTest {
    
    public static void main(String[] args) {
        System.out.println("=== 상태 패턴 연습문제 테스트 ===\n");
        
        // TODO: VendingMachine 인스턴스를 생성하세요
        // VendingMachine vendingMachine = new VendingMachine();
        
        System.out.println("❌ 아직 구현되지 않았습니다.");
        System.out.println("다음 클래스들을 구현해주세요:");
        System.out.println("1. VendingMachineState (인터페이스)");
        System.out.println("2. VendingMachine (컨텍스트 클래스)");
        System.out.println("3. IdleState (대기 상태)");
        System.out.println("4. CoinInsertedState (동전 투입 상태)");
        System.out.println("5. ProductSelectedState (상품 선택 상태)");
        System.out.println("6. DispensingState (배출 상태)");
        
        System.out.println("\n구현 완료 후 이 테스트 클래스의 주석을 해제하고 실행해보세요!");
        
        /*
        // 테스트 1: 기본 구매 플로우
        System.out.println("🧪 테스트 1: 기본 구매 플로우");
        vendingMachine.displayStatus();
        vendingMachine.insertCoin(1000);
        vendingMachine.selectProduct("A1");
        vendingMachine.dispenseProduct();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // 테스트 2: 거스름돈 반환
        System.out.println("🧪 테스트 2: 거스름돈 반환");
        vendingMachine.insertCoin(2000);
        vendingMachine.selectProduct("B2");
        vendingMachine.dispenseProduct();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // 테스트 3: 오류 상황
        System.out.println("🧪 테스트 3: 오류 상황");
        vendingMachine.selectProduct("A1"); // 동전 없이 상품 선택
        vendingMachine.insertCoin(500);
        vendingMachine.selectProduct("A1"); // 부족한 금액으로 상품 선택
        
        System.out.println("\n=== 테스트 완료 ===");
        */
    }
} 