# 상태 패턴(State Pattern) Best Practices

## 🎯 개요

상태 패턴을 구현할 때 자주 발생하는 실수들과 이를 개선하는 방법들을 정리한 가이드입니다.

## ❌ Bad Practices

### 1. **상태 객체에서 직접 필드 접근**

```java
// ❌ Bad: 상태 객체가 컨텍스트의 내부 구조를 직접 탐색
public class CoinInsertedState {
    @Override
    public void insertCoin(int amount) {
        // 디미터 법칙 위반: 객체 체인 탐색
        int currentBalance = vendingMachine.getCurrentBalance();
        vendingMachine.setCurrentBalance(currentBalance + amount);
        
        // 직접 상태 객체 생성 및 할당
        vendingMachine.currentState = new ProductSelectedState(vendingMachine);
    }
}
```

**문제점:**
- 디미터 법칙 위반
- 컨텍스트의 내부 구조에 강하게 결합
- 상태 전환 로직이 분산됨
- 테스트 어려움

### 2. **단순한 getter/setter 사용**

```java
// ❌ Bad: 의미 없는 getter/setter
public class VendingMachine {
    public void setState(VendingMachineState state) {
        this.currentState = state;
    }
    
    public VendingMachineState getCoinInsertedState() {
        return coinInsertedState;
    }
}

// 상태 객체에서
vendingMachine.setState(vendingMachine.getCoinInsertedState());
```

**문제점:**
- 의도가 명확하지 않음
- 상태 전환 로직이 캡슐화되지 않음
- 디미터 법칙 위반

### 3. **상태별 동작을 컨텍스트에 집중**

```java
// ❌ Bad: 모든 로직이 컨텍스트에 집중
public class VendingMachine {
    public void insertCoin(int amount) {
        if (currentState instanceof IdleState) {
            currentBalance += amount;
            currentState = coinInsertedState;
        } else if (currentState instanceof CoinInsertedState) {
            currentBalance += amount;
            // 상태 변경 없음
        } else {
            System.out.println("동전 투입 불가");
        }
    }
}
```

**문제점:**
- 거대한 if-else 문
- 새로운 상태 추가 시 기존 코드 수정 필요
- 단일 책임 원칙 위반
- 상태별 동작이 혼재

### 4. **상태 객체에서 비즈니스 로직 누락**

```java
// ❌ Bad: 상태 객체가 단순히 컨텍스트에 위임만 함
public class CoinInsertedState {
    @Override
    public void insertCoin(int amount) {
        // 단순 위임만 하고 상태별 고유 로직 없음
        vendingMachine.insertCoin(amount);
    }
}
```

**문제점:**
- 상태 패턴의 장점을 살리지 못함
- 상태별 동작 차이가 없음
- 의미 없는 중간 계층

## ✅ Good Practices

### 1. **컨텍스트 메서드를 통한 안전한 데이터 접근**

```java
// ✅ Good: 컨텍스트에서 의미있는 메서드 제공
public class VendingMachine {
    public void addBalance(int amount) {
        this.currentBalance += amount;
    }
    
    public boolean hasEnoughBalance(int requiredAmount) {
        return this.currentBalance >= requiredAmount;
    }
    
    public Product getProduct(String productId) {
        return products.get(productId);
    }
}

// 상태 객체에서
public class CoinInsertedState {
    @Override
    public void insertCoin(int amount) {
        vendingMachine.addBalance(amount);
        System.out.println("추가로 동전 " + amount + "원이 투입되었습니다.");
        // 상태는 그대로 유지
    }
}
```

**장점:**
- 디미터 법칙 준수
- 컨텍스트의 내부 구조 캡슐화
- 테스트 용이성

### 2. **의미있는 상태 전환 메서드**

```java
// ✅ Good: 상태 전환을 의미있는 메서드로 제공
public class VendingMachine {
    public void changeToCoinInsertedState() {
        this.currentState = coinInsertedState;
        System.out.println("상태가 변경되었습니다: 대기 → 동전 투입됨");
    }
    
    public void changeToProductSelectedState() {
        this.currentState = productSelectedState;
        System.out.println("상태가 변경되었습니다: 동전 투입됨 → 상품 선택됨");
    }
    
    public void changeToIdleState() {
        this.currentState = idleState;
        System.out.println("상태가 변경되었습니다: → 대기");
    }
}

// 상태 객체에서
public class IdleState {
    @Override
    public void insertCoin(int amount) {
        vendingMachine.addBalance(amount);
        vendingMachine.changeToCoinInsertedState();
    }
}
```

**장점:**
- 의도가 명확함
- 상태 전환 로직 캡슐화
- 디미터 법칙 완벽 준수

### 3. **상태별 고유한 동작 구현**

```java
// ✅ Good: 각 상태가 자신만의 고유한 동작을 가짐
public class IdleState {
    @Override
    public void insertCoin(int amount) {
        vendingMachine.addBalance(amount);
        vendingMachine.changeToCoinInsertedState();
        System.out.println("동전이 투입되어 동전 투입 상태로 변경되었습니다.");
    }
    
    @Override
    public void selectProduct(String productId) {
        System.out.println("❌ 오류: 동전을 먼저 투입해주세요.");
    }
}

public class CoinInsertedState {
    @Override
    public void insertCoin(int amount) {
        vendingMachine.addBalance(amount);
        // 상태 변경 없음 - 동전 투입 상태 유지
        System.out.println("추가로 동전 " + amount + "원이 투입되었습니다.");
    }
    
    @Override
    public void selectProduct(String productId) {
        Product product = vendingMachine.getProduct(productId);
        if (product == null) {
            System.out.println("❌ 오류: 존재하지 않는 상품입니다.");
            return;
        }
        
        if (!vendingMachine.hasEnoughBalance(product.getPrice())) {
            System.out.println("❌ 오류: 투입된 금액이 부족합니다.");
            return;
        }
        
        vendingMachine.setSelectedProduct(productId);
        vendingMachine.changeToProductSelectedState();
        System.out.println("상품이 선택되었습니다: " + product.getName());
    }
}
```

**장점:**
- 상태별 동작이 명확히 구분됨
- 새로운 상태 추가 시 기존 코드 수정 불필요
- 단일 책임 원칙 준수

### 4. **비즈니스 로직과 상태 전환의 적절한 분리**

```java
// ✅ Good: 비즈니스 로직과 상태 전환을 적절히 분리
public class ProductSelectedState {
    @Override
    public void dispenseProduct() {
        Product product = vendingMachine.getProduct(vendingMachine.getSelectedProduct());
        
        // 비즈니스 로직: 금액 차감
        vendingMachine.deductBalance(product.getPrice());
        
        // 상태 전환
        vendingMachine.changeToDispensingState();
        
        // 상태별 고유 동작
        System.out.println("상품 배출 중: " + product.getName());
    }
}
```

**장점:**
- 비즈니스 로직과 상태 전환이 명확히 구분됨
- 각 부분을 독립적으로 테스트 가능
- 유지보수성 향상

## 🎯 핵심 원칙

### 1. **디미터 법칙 준수**
- 객체의 내부 구조를 직접 탐색하지 말고, 필요한 메서드를 통해 접근
- `vendingMachine.addBalance(amount)` vs `vendingMachine.setCurrentBalance(vendingMachine.getCurrentBalance() + amount)`

### 2. **의미있는 메서드명**
- 단순한 getter/setter 대신 비즈니스 의도를 표현하는 메서드명 사용
- `changeToProductSelectedState()` vs `setState(getProductSelectedState())`

### 3. **상태별 고유한 동작**
- 각 상태 클래스는 해당 상태에서만 가능한 동작을 구현
- 상태별로 다른 동작과 상태 전환을 가져야 함

### 4. **캡슐화**
- 상태 객체는 컨텍스트의 내부 구조를 몰라도 동작할 수 있어야 함
- 컨텍스트는 상태 전환과 데이터 접근을 위한 안전한 인터페이스 제공

## 📚 참고 자료

- GoF 디자인 패턴 - 상태 패턴
- Clean Code - 디미터 법칙
- Domain-Driven Design - 도메인 로직의 위치
- SOLID 원칙 - 단일 책임 원칙, 개방-폐쇄 원칙

---

**💡 이 가이드를 따라 구현하면 상태 패턴의 장점을 최대한 활용할 수 있습니다!** 