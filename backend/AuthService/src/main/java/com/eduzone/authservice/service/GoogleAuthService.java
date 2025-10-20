package com.eduzone.authservice.service;

import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GoogleAuthService {

    @Value("${app.google.client-id}")
    private String googleClientId;

    public String verifyGoogleIdToken(String idTokenString) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(), new JacksonFactory())
                    .setAudience(java.util.Collections.singletonList(googleClientId))
                    .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken != null) {
                return idToken.getPayload().getEmail();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
