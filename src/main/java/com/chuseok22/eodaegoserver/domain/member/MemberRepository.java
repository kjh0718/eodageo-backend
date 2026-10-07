package com.chuseok22.eodaegoserver.domain.member;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, UUID> {
  Optional<Member> findBySocialTypeAndProviderId(SocialType socialType, String providerId);
}

