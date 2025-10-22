package net.yorksolutions.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "${client.url}")
public class TSGMemberController {
    /**
     * SECTION:
     * Required-Args Constructor
     * NOTE: All necessary 'Services' are included
     */
    // TODO - COMPLETE ME

    /**
     * PT01 - Handler for Verifying the OAuth-provided JWT
     * <p>
     * End-point: localhost:8080/api/auth/me
     * <p>
     * There is NO local login/logout end-point as that is handled between
     * React and Google OAuth.
     * <p>
     * This end-point MUST validate the JWT access token(s) (issuer, audience, JWKs)
     * @return Current User/Member Basic Info (derived from Token & DB Mapping)
     */
    @GetMapping("/api/auth/me")
    public String readMemberAuth() {
        // TODO - COMPLETE ME
        return "Auth. details accepted";
    }
}