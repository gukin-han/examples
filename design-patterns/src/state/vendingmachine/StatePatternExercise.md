# 상태 패턴(State Pattern) 연습문제

## 🎯 연습문제: 자동판매기 상태 관리 시스템

### 📋 문제 설명

자동판매기(Vending Machine)의 상태를 관리하는 시스템을 상태 패턴을 사용하여 구현해보세요.

### 🏗️ 요구사항

#### 1. 자동판매기의 상태들
- **대기 상태 (Idle)**: 동전이 투입되지 않은 상태
- **동전 투입 상태 (CoinInserted)**: 동전이 투입되었지만 상품이 선택되지 않은 상태  
- **상품 선택 상태 (ProductSelected)**: 상품이 선택되었지만 아직 배출되지 않은 상태
- **배출 상태 (Dispensing)**: 상품이 배출되는 상태

#### 2. 각 상태에서 가능한 동작
- **동전 투입 (insertCoin)**: 동전을 투입하는 기능
- **상품 선택 (selectProduct)**: 상품을 선택하는 기능
- **상품 배출 (dispenseProduct)**: 선택된 상품을 배출하는 기능
- **거스름돈 반환 (returnChange)**: 거스름돈을 반환하는 기능

#### 3. 상태 전환 규칙
```
[대기] ←→ [동전 투입됨] ←→ [상품 선택됨] → [배출 중] → [대기]
  ↑           ↓
  └─── 거스름돈 반환 ───┘
```

### 📝 구현해야 할 클래스들

#### 1. 상태 인터페이스
```java
public interface VendingMachineState {
    void insertCoin(int amount);
    void selectProduct(String productId);
    void dispenseProduct();
    void returnChange();
    void displayStatus();
}
```

#### 2. 자동판매기 클래스 (컨텍스트)
```java
public class VendingMachine {
    private VendingMachineState currentState;
    private int currentBalance;
    private String selectedProduct;
    private Map<String, Product> products;
    
    // TODO: 생성자, 상태 변경 메서드, getter/setter 구현
    // TODO: 사용자 인터페이스 메서드들 구현
}
```

#### 3. 각 상태 클래스들
- `IdleState`: 대기 상태 구현
- `CoinInsertedState`: 동전 투입 상태 구현  
- `ProductSelectedState`: 상품 선택 상태 구현
- `DispensingState`: 배출 상태 구현

### 🎮 상품 정보
| 상품 ID | 상품명 | 가격 |
|---------|--------|------|
| A1 | 콜라 | 1,000원 |
| A2 | 사이다 | 1,000원 |
| B1 | 커피 | 800원 |
| B2 | 물 | 500원 |

### 💡 구현 힌트

1. **상태 인터페이스 설계**: 모든 상태에서 공통으로 사용할 메서드들을 정의하세요.

2. **컨텍스트 클래스 설계**: 
   - 현재 상태를 관리하는 필드
   - 상태 변경 메서드
   - 각 상태 객체에 대한 참조

3. **각 상태 클래스 구현**:
   - 해당 상태에서 가능한 동작만 구현
   - 불가능한 동작은 적절한 오류 메시지 출력
   - 상태 전환 시 컨텍스트의 상태 변경 메서드 호출

4. **상태별 동작 규칙**:
   - **대기 상태**: 동전 투입만 가능
   - **동전 투입 상태**: 상품 선택, 추가 동전 투입, 거스름돈 반환 가능
   - **상품 선택 상태**: 상품 배출만 가능
   - **배출 상태**: 거스름돈 반환만 가능

### 🧪 테스트 시나리오

구현 완료 후 다음 시나리오로 테스트해보세요:

1. **기본 구매 플로우**: 동전 투입 → 상품 선택 → 상품 배출
2. **거스름돈 반환**: 동전 투입 → 거스름돈 반환
3. **오류 상황**: 동전 없이 상품 선택, 부족한 금액으로 상품 선택 등

### 🎯 학습 목표

- 상태 패턴의 기본 구조 이해
- 상태별 동작 분리와 캡슐화
- 상태 전환 로직 구현
- 조건문 제거를 통한 코드 개선

### 📚 참고 자료

- GoF 디자인 패턴 - 상태 패턴
- 상태 머신(State Machine) 개념
- 전략 패턴과의 차이점

---

**💪 도전해보세요! 상태 패턴을 직접 구현하면서 패턴의 장점을 체감해보세요.** 