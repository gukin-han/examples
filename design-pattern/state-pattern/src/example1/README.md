상태패턴을 연습할 수 있는 문제를 만들어 보겠습니다. 아래 문제는 실생활의 간단한 시나리오를 모델링하며 상태패턴을 적용해 볼 수 있는 좋은 연습문제입니다.

---

### 문제: **자판기 시뮬레이터**

### 배경

자판기는 다음과 같은 동작 상태를 가집니다:

1. **동전 없음(No Coin)**: 자판기에 동전이 없는 상태. 사용자에게 동전을 넣으라는 메시지를 표시합니다.
2. **동전 있음(Has Coin)**: 동전이 투입된 상태. 사용자에게 음료를 선택할 수 있도록 안내합니다.
3. **음료 선택 중(Selecting Drink)**: 사용자가 음료를 선택 중인 상태. 음료를 선택하면 해당 음료가 배출됩니다.
4. **재고 없음(Out of Stock)**: 자판기에 재고가 없을 때, 사용자는 음료를 받을 수 없습니다.

### 요구사항

1. 상태패턴을 사용하여 자판기의 상태를 구현하세요.
2. 자판기는 다음 메서드를 가집니다:
    - `insertCoin()`: 동전을 넣습니다.
    - `selectDrink(String drinkName)`: 음료를 선택합니다.
    - `dispenseDrink()`: 선택한 음료를 배출합니다.
    - `refillStock(int count)`: 재고를 추가합니다.
3. 각 상태에서 위 메서드 호출 시 수행되는 동작이 다릅니다. 예를 들어:
    - 동전이 없는 상태에서 `selectDrink()`를 호출하면 "동전을 먼저 넣으세요."라는 메시지가 출력됩니다.
    - 재고가 없는 상태에서 `insertCoin()`을 호출하면 "재고가 없습니다."라는 메시지가 출력됩니다.
4. 자판기는 여러 종류의 음료를 제공하며, 음료별로 재고를 관리합니다.

### 추가 조건

- 상태 전환 로직은 상태 객체 안에서 처리해야 합니다.
- `VendingMachine` 클래스는 상태에 의존하지 않고 상태 객체와만 상호작용해야 합니다.

---

### 목표

- 상태패턴을 사용하여 자판기의 상태와 동작을 구현하세요.
- 상태 객체 간 전환을 자연스럽게 처리하고, `VendingMachine`의 코드가 깔끔하게 유지되도록 하세요.

---

### 확장 과제

1. 음료 선택 시 잔액 확인 기능 추가 (예: 동전이 부족하면 음료를 배출하지 않음).
2. 다양한 음료의 가격 설정 기능 추가.
3. 로그 기록 기능 추가 (모든 상태 전환과 동작을 기록).

---

이 문제를 통해 상태패턴의 핵심인 **상태 전환과 객체의 책임 분리**를 연습할 수 있습니다. 필요하면 구현 중 도움을 요청하세요!