package com.chuseok22.eodaegoserver.domain.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
  private final SecretKey key;
  private final long accessExpMillis;
  private final Clock clock;

  public JwtProvider(@Value("${jwt.secret-key}") String secret,
                     @Value("${jwt.access-exp-millis}") long accessExpMillis,
                     Clock clock) {
    this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    this.accessExpMillis = accessExpMillis;
    this.clock = clock;
  }

  public String createAccessToken(UUID memberId) {
    Instant now = Instant.now(clock);
    return Jwts.builder()
        .issuer("eodaego-week5-demo")
        .subject(memberId.toString())
        .claim("role", "USER")
        .issuedAt(Date.from(now))
        .expiration(Date.from(now.plusMillis(accessExpMillis)))
        .signWith(key)
        .compact();
  }
}
