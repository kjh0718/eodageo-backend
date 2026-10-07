# PR 초안 2 — 5주차 로그인 1차 경로

기준 브랜치: `presentation/week5-design`
제안 브랜치: `presentation/week5-login-v1`
상태: 로컬 초안. PR·리뷰·병합 미생성.

## 목적

앱이 Firebase 소셜 로그인 뒤 자체 액세스 토큰과 온보딩 분기 값을 받는 경로를 재구성한다.

## 변경

- Firebase ID Token 검증과 Google/Apple 제공자 일치 확인
- `(socialType, providerId)`로 회원 조회 또는 생성
- 액세스 토큰, `firstLogin`, `requiresAgreement`, 닉네임, 회원 ID 응답
- 시연용 H2 DB와 환경 변수 기반 비밀 설정

## 범위 설명

원본의 재발급·로그아웃·관리자 기능, 랜덤 닉네임 및 충돌 재시도는 포함하지 않는다. UUID 유래 임시 닉네임은 이 재구성을 실행 가능하게 하려는 현재 시점의 선택이다. 실제 Firebase 키와 실제 소셜 토큰은 저장소에 넣지 않는다.

## 검증

2026-10-07에 Windows에서 `.\gradlew.bat compileJava bootJar --no-daemon` 실행 결과 `BUILD SUCCESSFUL`(4개 작업 실행)을 확인했다. 실제 Firebase 로그인 호출은 유효한 자격 증명과 클라이언트 발급 ID Token이 없어 아직 확인하지 못했다. 이 초안에 과거 리뷰·병합 사실을 기재하지 않는다.
