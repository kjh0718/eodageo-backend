# 어대GO Backend

어대GO Spring Boot 서버의 5주차 작업 범위입니다. 회원·도감·시설·코스 데이터 설계와 소셜 로그인 1차 경로를 다룹니다.

## 작업 범위

- 회원: UUID 식별자, 소셜 계정과 닉네임 유일 제약, 기기 정보와 약관 동의 필드
- 도감: 크롤링 원본 `CatalogSource`, 표시 항목 `CatalogItem`, 회원별 수집 기록 `MemberCatalogCollection` 설계
- 시설·코스: AI 시설 식별자 동기화, 방문 장소 스냅샷과 즐겨찾기 구조 설계
- 로그인: Firebase Google/Apple ID Token 검증, 회원 조회·생성, JWT 액세스 토큰과 온보딩 분기 응답

도감·시설·코스의 API는 이 범위에 포함되지 않습니다. 설계 내용은 `docs/week5-design.md`에 있습니다.

## 브랜치

- `main`: Java 21 / Spring Boot 4.1 기반 프로젝트
- `presentation/week5-design`: 설계 문서와 협업 규칙
- `presentation/week5-login-v1`: `POST /api/v1/auth/login` 구현

## 로컬 실행

로그인 브랜치에서 Java 21, Firebase 서비스 계정, 32바이트 이상의 JWT 비밀키를 준비합니다. 서비스 계정 JSON은 저장소 밖에 둡니다.

```powershell
$env:GOOGLE_APPLICATION_CREDENTIALS = 'C:\path\outside-repo\firebase-service-account.json'
$env:JWT_SECRET_KEY = '<32-byte-or-longer-secret>'
.\gradlew.bat bootRun
```

`POST http://localhost:8080/api/v1/auth/login` 요청 예시:

```json
{
  "idToken": "<firebase-id-token>",
  "socialType": "GOOGLE",
  "deviceType": "ANDROID",
  "deviceId": "demo-device"
}
```

응답은 `accessToken`, `firstLogin`, `requiresAgreement`, `nickname`, `userId`를 담습니다. H2 인메모리 DB를 사용하므로 서버 재시작 시 회원 데이터는 지워집니다. 약관 수정 API가 없어 현재 구현에서 `requiresAgreement`는 계속 `true`입니다.

재발급·로그아웃·관리자 로그인과 닉네임 충돌 재시도는 이 범위에 포함되지 않습니다. 회원 저장 시 NOT NULL 제약을 만족시키는 닉네임은 UUID에서 만든 임시값입니다.

## 출처

이 저장소는 2026-10-07에 김재현·강지윤의 5주차 학습보고서(2026-09-29~10-05)와 [원본 BE 저장소](https://github.com/eodaego/eodaego-BE)의 커밋 `de0766f06b6fec2fa381cab0f06179a02f73eb78`을 참고해 별도로 구성했습니다. 현재 브랜치와 커밋은 당시의 이력이나 과거 PR·리뷰를 나타내지 않습니다. H2 DB, UUID 임시 닉네임, 정적 `/api/v1` 경로는 이 저장소의 실행을 위한 선택입니다.
