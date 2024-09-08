# 🐿 샘플 템플릿

> 🤔 매 프로젝트마다 옛날 리포지토리 복사해서 쓰는게 귀찮아서 만든 템플릿입니다.

## 목차

- [🐥 컨벤션 가이드](#-컨벤션-가이드)
- [🐶 템플릿 사용 방법](#-템플릿-사용-방법)
- [🐸 실행 방법](#-실행-방법)
- [🐹 개발 환경](#-개발-환경)
- [👻 상세 개발 환경](#-상세-개발-환경)
- [🐰 프로젝트 상세](#-프로젝트-상세)
- [🐳 ERD](#-erd)
- [🐙 API docs](#-api-docs)
- [🐬 인프라 구조](#-인프라-구조)

## 🐥 컨벤션 가이드

- [여기](./conventions)를 참고해주세요.

## 🐶 템플릿 사용 방법

![템플릿 사용 방법](./docs/images/conventions/create-template.png)

1. `Use this template` 버튼을 누릅니다.
2. `Create a new repository` 버튼을 누릅니다.
3. 새로운 리포지토리 이름을 입력하고 생성합니다.

## 🐸 실행 방법

1. 아래의 환경 변수 설정 (프로젝트에 맞게 수정)
    ```dotenv
    # 아래는 예시입니다. 프로젝트에 맞게 수정해주세요.    
    JWT_SECRET_KEY={JWT_SECRET_KEY}
    ```
    - `구성 편집` -> `빌드 및 실행` -> `옵션 수정` -> `환경 변수` 선택 -> 환경 변수에 아래의 형식으로 작성
    - `키1=값1;키2=값2`
2. 프로젝트에 맞게 [docker-compose.yml](./docker-compose.yml) 수정
3. 도커 실행
4. 스프링 실행
    - `docker compose support` 라이브러리가 자동으로 컨테이너를 실행 및 종료합니다.

## 🐹 개발 환경

| 분류         | 상세                                  |
|------------|:------------------------------------|
| IDE        | IntelliJ                            |
| Language   | Java 17                             |
| Framework  | Spring Boot 3.3.3                   |
| Repository | PostgreSQL 16.4, H2 In-memory(Test) |
| Build Tool | Gradle 8.8                          |
| Infra      | EC2, Docker, Github Actions         |

## 👻 상세 개발 환경

### Dependencies

- Spring WebMVC
- Spring Validation
- Spring Security
- Spring Data Jpa
- Spring Data Redis
- jjwt 0.12.5
- QueryDSL 5.0.0
- mapStruct 1.5.5.Final
- Lombok
- JUnit
- Swagger 2.6.0
- Jacoco

## 🐰 프로젝트 상세

> 여기에 프로젝트 상세 소개를 작성하시면 됩니다.

## 🐳 ERD

![ERD](./docs/images/sample-squirrel.jpg)

- [ErdCloud](https://www.google.co.kr/)

## 🐙 API docs

- [Swagger UI](https://www.google.co.kr/)

## 🐬 인프라 구조

![Infra](./docs/images/sample-squirrel.jpg)
