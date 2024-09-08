# sample2

## 디렉토리 구조

- presentation: 프레젠테이션 계층
    - controller: 컨트롤러
- application: 응용 계층
    - service: 응용 서비스
- domain: 도메인 계층
    - entity: 엔티티
    - value: 값 객체
    - service: 도메인 서비스
- infrastructure: 인프라스트럭처 계층
    - repository: 리포지토리
- dto: 데이터 전송 객체
    - request: 요청 객체
    - response: 응답 객체
- mapper: 매퍼
- property: 프로퍼티

## 응용 서비스 vs 도메인 서비스

### 응용 서비스

- 트랜잭션 처리 및 도메인 객체 간 흐름 제어

### 도메인 서비스

- 여러 애그리거트를 필요로 하는 로직을 처리하거나, 하나의 애그리거트에서 처리하기 복잡한 로직을 담당

## 리포지토리

- Repository는 도메인 디렉토리에 위치하는 것이 맞으나, JPA를 사용할 경우 JPA Repository를 사용하게 되면 인프라스트럭처 계층에 위치하도록 한다.
