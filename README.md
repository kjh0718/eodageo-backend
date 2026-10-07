# 어대GO BE 5주차 발표용 재구성

이 저장소는 5주차 보고서의 설계 및 로그인 1차 범위를 현재 시점에 **재구성한 시연용 저장소**입니다. 과거 5주차에 실제로 존재했던 커밋·브랜치·PR 이력이 아닙니다. 원본 [`eodaego-BE`](https://github.com/eodaego/eodaego-BE)의 이력은 수정하지 않았습니다.

원본 참고 커밋: `de0766f06b6fec2fa381cab0f06179a02f73eb78`. 구현의 요청 형식·Firebase 제공자 검사·회원 필드·JWT 발급 방식은 이 커밋을 참고해 5주차 범위로 새로 작성했습니다. 주차 기준은 김재현·강지윤의 5주차 학습보고서(2026-09-29~10-05)입니다.

## 구성

- `main`: 독립 실행 Gradle 프로젝트
- `presentation/week5-design`: 회원·도감·시설·코스 설계 및 협업 규칙 문서
- `presentation/week5-login-v1`: Firebase 토큰 검증 → 회원 조회·생성 → 자체 액세스 토큰 응답

브랜치는 PR 검토를 위한 **초안**입니다. 실제 PR·리뷰·병합은 아직 없습니다.

## 실행 범위

로그인은 `POST /api/v1/auth/login` 하나입니다. Firebase Google/Apple ID Token 검증과 요청한 제공자와의 일치 확인, 회원 저장, 액세스 토큰 발급, `firstLogin`·`requiresAgreement` 응답을 포함합니다. DB는 시연용 H2 메모리 DB입니다. Firebase 실제 자격 증명과 자체 JWT 비밀키가 있어야 로그인 호출이 됩니다.

재발급·로그아웃·관리자 로그인·도감/시설/코스 API 및 닉네임 충돌 재시도는 5주차 코드에서 제외했습니다. 5주차 보고서의 설계 내용은 `docs/`에 있으며 API로 구현됐다는 의미가 아닙니다.

