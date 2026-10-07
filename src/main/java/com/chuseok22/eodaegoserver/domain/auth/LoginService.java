package com.chuseok22.eodaegoserver.domain.auth;

import com.chuseok22.eodaegoserver.domain.member.Member;
import com.chuseok22.eodaegoserver.domain.member.MemberRepository;
import com.google.firebase.auth.FirebaseToken;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {
  private final FirebaseTokenVerifier tokenVerifier;
  private final MemberRepository members;
  private final JwtProvider jwtProvider;

  @Transactional
  public LoginController.LoginResponse login(LoginController.LoginRequest request) {
    FirebaseToken token = tokenVerifier.verify(request.idToken(), request.socialType());
    Member member = members.findBySocialTypeAndProviderId(request.socialType(), token.getUid())
        .orElseGet(() -> createMember(request, token));
    boolean firstLogin = member.isFirstLogin();
    member.setFirstLogin(false);
    member.setDeviceType(request.deviceType());
    member.setDeviceId(request.deviceId());
    if (request.fcmToken() != null) {
      member.setFcmToken(request.fcmToken());
    }
    boolean requiresAgreement = !(member.isPrivacyPolicyAgreed()
        && member.isLocationInfoAgreed() && member.isTermsOfServiceAgreed());
    return new LoginController.LoginResponse(jwtProvider.createAccessToken(member.getId()),
        firstLogin, requiresAgreement, member.getNickname(), member.getId());
  }

  private Member createMember(LoginController.LoginRequest request, FirebaseToken token) {
    UUID id = UUID.randomUUID();
    // ponytail: 시연용 UUID 닉네임. 제품용 이름 생성·충돌 재시도는 6주차 범위다.
    Member member = Member.builder()
        .id(id)
        .email(token.getEmail())
        .nickname("회원" + id.toString().replace("-", ""))
        .socialType(request.socialType())
        .providerId(token.getUid())
        .role("USER")
        .firstLogin(true)
        .deviceType(request.deviceType())
        .deviceId(request.deviceId())
        .fcmToken(request.fcmToken())
        .build();
    return members.save(member);
  }
}

