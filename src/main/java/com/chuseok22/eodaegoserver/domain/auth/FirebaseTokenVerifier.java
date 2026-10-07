package com.chuseok22.eodaegoserver.domain.auth;

import com.chuseok22.eodaegoserver.domain.member.SocialType;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class FirebaseTokenVerifier {
  private final FirebaseAuth firebaseAuth;

  public FirebaseToken verify(String idToken, SocialType requestedType) {
    FirebaseToken token;
    try {
      token = firebaseAuth.verifyIdToken(idToken);
    } catch (FirebaseAuthException e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Firebase ID token", e);
    }

    Object claim = token.getClaims().get("firebase");
    Object provider = claim instanceof Map<?, ?> firebase ? firebase.get("sign_in_provider") : null;
    String expected = requestedType == SocialType.GOOGLE ? "google.com" : "apple.com";
    if (!expected.equals(provider)) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Social provider does not match token");
    }
    return token;
  }
}

