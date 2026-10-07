# 어대GO BE 5주차 발표용 재구성

이 저장소는 5주차 보고서의 설계 및 로그인 1차 범위를 현재 시점에 **재구성한 시연용 저장소**입니다. 과거 5주차에 실제로 존재했던 커밋·브랜치·PR 이력이 아닙니다. 원본 [`eodaego-BE`](https://github.com/Chuseok22/eodaego-BE)의 이력은 수정하지 않았습니다.

원본 참고 커밋: `de0766f06b6fec2fa381cab0f06179a02f73eb78`. 구현의 요청 형식·Firebase 제공자 검사·회원 필드·JWT 발급 방식은 이 커밋을 참고해 5주차 범위로 새로 작성했습니다. 주차 기준은 김재현·강지윤의 5주차 학습보고서(2026-09-29~10-05)입니다.

## 구성

- `main`: 독립 실행 Gradle 프로젝트
- `presentation/week5-design`: 회원·도감·시설·코스 설계 및 협업 규칙 문서
- `presentation/week5-login-v1`: Firebase 토큰 검증 → 회원 조회·생성 → 자체 액세스 토큰 응답

브랜치는 PR 검토를 위한 **초안**입니다. 실제 PR·리뷰·병합은 아직 없습니다.

## 실행 범위

로그인은 `POST /api/v1/auth/login` 하나입니다. Firebase Google/Apple ID Token 검증과 요청한 제공자와의 일치 확인, 회원 저장, 액세스 토큰 발급, `firstLogin`·`requiresAgreement` 응답을 포함합니다. DB는 시연용 H2 메모리 DB입니다. Firebase 실제 자격 증명과 자체 JWT 비밀키가 있어야 로그인 호출이 됩니다.

재발급·로그아웃·관리자 로그인·도감/시설/코스 API 및 닉네임 충돌 재시도는 5주차 코드에서 제외했습니다. 5주차 보고서의 설계 내용은 `docs/`에 있으며 API로 구현됐다는 의미가 아닙니다.

## 로컬 실행

Java 21과 Firebase 서비스 계정 자격 증명이 필요합니다. 서비스 계정 JSON은 저장소 밖에 두고 환경 변수 `GOOGLE_APPLICATION_CREDENTIALS`에 절대 경로를 설정합니다. `JWT_SECRET_KEY`에는 32바이트 이상인 별도 비밀 문자열을 설정합니다. 실제 값은 Git에 기록하지 않습니다.

```powershell
$env:GOOGLE_APPLICATION_CREDENTIALS = 'C:\path\outside-repo\firebase-service-account.json'
$env:JWT_SECRET_KEY = '<32-byte-or-longer-secret>'
.\gradlew.bat bootRun
```

`POST http://localhost:8080/api/v1/auth/login` 요청 예시는 다음과 같습니다. `idToken`에는 클라이언트가 실제로 얻은 Firebase ID Token을 넣습니다.

```json
{
  "idToken": "<firebase-id-token>",
  "socialType": "GOOGLE",
  "deviceType": "ANDROID",
  "deviceId": "demo-device"
}
```

응답 필드는 `accessToken`, `firstLogin`, `requiresAgreement`, `nickname`, `userId`입니다. 시연용 DB가 메모리에 있어 재시작 시 회원 기록은 지워집니다. 별도 회원·약관 수정 API가 없으므로 `requiresAgreement`는 이 저장소에서 계속 `true`입니다.

**재구성상의 선택:** 5주차 보고서에는 닉네임 자동 생성과 충돌 처리를 다음 주로 미뤘다고 기록되어 있습니다. 실행 가능한 회원 엔티티의 NOT NULL 조건을 만족시키기 위해 여기서는 UUID에서 유래한 임시 닉네임을 저장합니다. 이는 당시 구현을 주장하는 코드가 아닙니다. H2 메모리 DB와 정적 `/api/v1` 경로도 독립 시연을 위한 선택입니다.
