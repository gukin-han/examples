# HR 시스템 상태 패턴(State Pattern) 연습문제

## 🎯 연습문제: 직원 근무 상태 관리 시스템

### 📋 문제 설명

회사의 HR 시스템에서 직원의 근무 상태를 관리하는 시스템을 상태 패턴을 사용하여 구현해보세요.

### 🏗️ 요구사항

#### 1. 직원의 상태들
- **근무 전 (BeforeWork)**: 아직 근무를 시작하지 않은 상태
- **근무 중 (Working)**: 현재 근무 중인 상태
- **자리비움 (OnBreak)**: 휴식 중인 상태
- **휴가 신청 중 (VacationRequested)**: 휴가를 신청한 상태
- **휴가 중 (OnVacation)**: 휴가를 사용 중인 상태

#### 2. 각 상태에서 가능한 동작
- **근무 시작 (startWork)**: 근무를 시작하는 기능
- **휴식 시작 (takeBreak)**: 휴식을 시작하는 기능
- **휴식 종료 (returnFromBreak)**: 휴식을 마치고 근무를 재개하는 기능
- **휴가 신청 (requestVacation)**: 휴가를 신청하는 기능
- **휴가 승인 (approveVacation)**: 휴가를 승인하는 기능
- **휴가 거절 (rejectVacation)**: 휴가를 거절하는 기능
- **휴가 종료 (returnFromVacation)**: 휴가를 마치고 근무를 재개하는 기능
- **상태 확인 (displayStatus)**: 현재 상태를 확인하는 기능

#### 3. 상태 전환 규칙
```
[근무 전] → [근무 중] → [자리비움] → [근무 중]
    ↓           ↓           ↓
[휴가 신청] → [휴가 중] → [근무 중]
```

### 📝 구현해야 할 클래스들

#### 1. 상태 인터페이스
```java
public interface EmployeeState {
    void startWork();
    void takeBreak();
    void returnFromBreak();
    void requestVacation();
    void approveVacation();
    void rejectVacation();
    void returnFromVacation();
    void displayStatus();
}
```

#### 2. 직원 클래스 (컨텍스트)
```java
public class Employee {
    private EmployeeState currentState;
    private String employeeId;
    private String name;
    private LocalDateTime workStartTime;
    private LocalDateTime breakStartTime;
    private LocalDateTime vacationStartTime;
    
    // TODO: 생성자, 상태 변경 메서드, getter/setter 구현
    // TODO: 사용자 인터페이스 메서드들 구현
}
```

#### 3. 각 상태 클래스들
- `BeforeWorkState`: 근무 전 상태 구현
- `WorkingState`: 근무 중 상태 구현
- `OnBreakState`: 자리비움 상태 구현
- `VacationRequestedState`: 휴가 신청 중 상태 구현
- `OnVacationState`: 휴가 중 상태 구현

### 🎮 직원 정보
| 필드 | 설명 |
|------|------|
| employeeId | 직원 ID |
| name | 직원 이름 |
| workStartTime | 근무 시작 시간 |
| breakStartTime | 휴식 시작 시간 |
| vacationStartTime | 휴가 시작 시간 |

### 💡 구현 힌트

1. **상태 인터페이스 설계**: 모든 상태에서 공통으로 사용할 메서드들을 정의하세요.

2. **컨텍스트 클래스 설계**: 
   - 현재 상태를 관리하는 필드
   - 상태 변경 메서드
   - 각 상태 객체에 대한 참조
   - 직원 정보 관리

3. **각 상태 클래스 구현**:
   - 해당 상태에서 가능한 동작만 구현
   - 불가능한 동작은 적절한 오류 메시지 출력
   - 상태 전환 시 컨텍스트의 상태 변경 메서드 호출

4. **상태별 동작 규칙**:
   - **근무 전**: 근무 시작만 가능
   - **근무 중**: 휴식 시작, 휴가 신청 가능
   - **자리비움**: 휴식 종료만 가능
   - **휴가 신청 중**: 휴가 승인/거절만 가능
   - **휴가 중**: 휴가 종료만 가능

### 🧪 테스트 시나리오

구현 완료 후 다음 시나리오로 테스트해보세요:

1. **기본 근무 플로우**: 근무 시작 → 휴식 → 휴식 종료
2. **휴가 플로우**: 휴가 신청 → 휴가 승인 → 휴가 종료
3. **오류 상황**: 근무 전에 휴식 시작, 휴가 중에 근무 시작 등

### 🎯 학습 목표

- 상태 패턴의 기본 구조 이해
- 상태별 동작 분리와 캡슐화
- 상태 전환 로직 구현
- 조건문 제거를 통한 코드 개선
- 실무 도메인에 상태 패턴 적용

### 📚 참고 자료

- GoF 디자인 패턴 - 상태 패턴
- 상태 머신(State Machine) 개념
- HR 시스템 도메인 지식
- SOLID 원칙

### 🏢 실제 HR 시스템에서의 활용

이 연습문제는 실제 HR 시스템에서 다음과 같이 활용될 수 있습니다:

1. **출퇴근 관리**: 직원의 출근, 퇴근, 휴식 상태 관리
2. **휴가 관리**: 연차, 병가, 공가 등의 승인 프로세스
3. **근무 시간 계산**: 각 상태별 시간을 기록하여 근무 시간 계산
4. **알림 시스템**: 상태 변경 시 관련자에게 알림 발송

---

**💪 도전해보세요! HR 시스템에 상태 패턴을 적용하면서 실무 활용 방법을 체득해보세요.** 