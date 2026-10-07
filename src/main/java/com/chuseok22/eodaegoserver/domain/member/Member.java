package com.chuseok22.eodaegoserver.domain.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = {
    @UniqueConstraint(name = "uk_member_social_provider", columnNames = {"social_type", "provider_id"}),
    @UniqueConstraint(name = "uk_member_nickname", columnNames = "nickname")
})
public class Member {
  @Id
  private UUID id;

  private String email;

  @Column(nullable = false)
  private String nickname;

  @Enumerated(EnumType.STRING)
  @Column(name = "social_type", nullable = false)
  private SocialType socialType;

  @Column(name = "provider_id", nullable = false)
  private String providerId;

  @Column(nullable = false)
  private String role;

  @Column(nullable = false)
  private boolean firstLogin;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private DeviceType deviceType;

  @Column(nullable = false)
  private String deviceId;

  private String fcmToken;

  @Column(nullable = false)
  private boolean privacyPolicyAgreed;

  @Column(nullable = false)
  private boolean locationInfoAgreed;

  @Column(nullable = false)
  private boolean termsOfServiceAgreed;

  @Column(nullable = false)
  private boolean marketingAgreed;
}
