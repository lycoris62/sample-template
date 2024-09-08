# 깃 브랜치 전략 가이드

## [Github Flow](https://docs.github.com/ko/get-started/using-github/github-flow) 사용

![Github Flow](../../docs/images/conventions/github-flow.png)*[출처](https://gist.github.com/Tlaloc-Es/993a6970c2f79a11cd36fc4457de0295)*

### 특징

- main 브랜치만을 고정적으로 유지
    - PR을 제외하고는 직접적인 커밋을 금지
    - PR을 통해 코드 리뷰 및 테스트 진행
    - 배포 가능한 상태를 유지
- 각 기능별로 main에서 브랜치를 생성하여 작업
    - 기능 개발이 완료되면 main 브랜치로 PR을 생성
    - feat, fix 등의 접두어를 사용하여 브랜치 이름을 작성
        - ex) feat/user-authentication, fix/bug-in-validation
