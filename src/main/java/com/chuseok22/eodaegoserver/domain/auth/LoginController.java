package com.chuseok22.eodaegoserver.domain.auth;

import com.chuseok22.eodaegoserver.domain.member.DeviceType;
import com.chuseok22.eodaegoserver.domain.member.SocialType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class LoginController {
  private final LoginService loginService;

  @PostMapping("/login")
  public LoginResponse login(@Valid @RequestBody LoginRequest request) {
    return loginService.login(request);
  }

  public record LoginRequest(@NotBlank String idToken,
                             @NotNull SocialType socialType,
                             @NotNull DeviceType deviceType,
                             @NotBlank String deviceId,
                             String fcmToken) {}

  public record LoginResponse(String accessToken, boolean firstLogin,
                              boolean requiresAgreement, String nickname,
                              UUID userId) {}
}

