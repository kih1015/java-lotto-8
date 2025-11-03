# 로또

### ✨기능 요구 사항

로또 구입 금액을 입력 받는다.
- 입력 메시지를 출력한다.
- 로또 구입 금액을 입력 받는다.
- 구입 금액은 1000원 단위로 입력 받으며, 1000원으로 나누어 떨어지지 않는 경우 예외 처리한다.

로또를 발행한다.
- 로또 번호의 숫자 범위는 1~45까지 이다.
- 1개의 로또를 발행할 때 중복되지 않는 6개의 숫자를 뽑는다.
- 로또 1장의 가격은 1000원이다. 

구매 내역을 조회한다.

구매 내역을 출력한다.
- 몇 개의 로또를 구매했는지 출력한다.
- 로또 번호를 오름차순으로 출력한다.

당첨 번호와 보너스 번호를 입력 받는다.
- 당첨 번호를 입력 받는다. 번호는 쉼표(,)를 기준으로 구분한다.
  - 숫자가 아닐 경우 예외처리한다.
- 보너스 번호를 입력받는다.
  - 숫자가 아닐 경우 예외처리한다.
  - 보너스 번호가 당첨 번호와 중복될 경우 예외처리한다.

당첨 번호와 보너스 번호를 저장한다.

당첨 통계를 계산한다.
- 각 등수에 해당하는 로또가 몇개인지 계산한다.
- 총 수익률을 계산한다.

당첨 통계를 출력한다.
- 당첨 내역을 출력한다.
- 총 수익률을 출력한다. 수익률은 소수점 둘째 자리에서 반올림한다.

---

## 구현 내용

### 아키텍처 개요

계층형 아키텍처(Layered Architecture)를 적용하여 각 계층의 책임을 명확히 분리하였습니다.

```
Presentation Layer (View)
      ↓
Service Layer
      ↓
Domain Layer
      ↓
Repository Layer
```

### 패키지 구조

#### 1. Domain Layer (`lotto.domain`)
핵심 비즈니스 로직과 도메인 모델을 담당합니다.

- **Value Objects**
  - `LottoNumber`: 1~45 범위의 로또 번호를 표현하는 불변 객체
  - `Money`: 1,000원 단위의 금액을 표현하는 불변 객체
  - `Rank`: 로또 등수와 상금을 정의한 열거형 (1등~5등)

- **Entity Classes**
  - `Lotto`: 6개의 중복되지 않는 로또 번호를 가진 로또 한 장
  - `WinningLotto`: 당첨 번호(6개)와 보너스 번호(1개)를 캡슐화
  - `LottoBundle`: 구매한 여러 장의 로또를 묶은 묶음
  - `VendingMachine`: 로또 발행기 (LottoGenerator를 사용하여 로또 생성)
  - `Statistics`: 당첨 통계 (등수별 당첨 개수, 수익률 계산)

#### 2. Service Layer (`lotto.service`)
비즈니스 로직의 흐름을 제어하며, CQRS 패턴을 적용했습니다.

- `LottoCommandService`: 쓰기 작업 담당 (로또 구매, 통계 계산)
- `LottoQueryService`: 읽기 작업 담당 (구매 내역 조회, 통계 조회)

#### 3. Repository Layer (`lotto.repository`)
데이터 저장 및 조회를 담당합니다.

- **Interfaces**
  - `LottoBundleRepository`: 로또 묶음 저장소 인터페이스
  - `StatisticsRepository`: 통계 저장소 인터페이스

- **Implementations**
  - `InMemoryLottoBundleRepository`: 메모리 기반 로또 묶음 저장소
  - `InMemoryStatisticsRepository`: 메모리 기반 통계 저장소

#### 4. Presentation Layer (`lotto.view`)
사용자 입출력을 담당합니다.

- `ConsoleReader`: 사용자 입력 처리
- `ConsoleWriter`: 결과 출력 처리

#### 5. DTO Layer (`lotto.dto`)
계층 간 데이터 전송을 위한 객체입니다.

- `PurchaseHistoryDto`: 구매 내역 데이터 전송
- `StatisticsDto`: 통계 데이터 전송

#### 6. Generator
로또 번호 생성 전략을 정의합니다.

- `LottoGenerator`: 로또 생성 인터페이스 (Strategy Pattern)
- `RandomLottoGenerator`: 랜덤 로또 생성 구현체

#### 7. Factory
객체 생성 및 의존성 주입을 담당합니다.

- `LottoAppFactory`: 애플리케이션 객체 생성 팩토리
