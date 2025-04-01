package ru.ilya.shopcraftercore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ilya.shopcraftercore.dto.OtpResponse;
import ru.ilya.shopcraftercore.dto.RefreshTokenRequest;
import ru.ilya.shopcraftercore.dto.VerifyOtpResponse;
import ru.ilya.shopcraftercore.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public OtpResponse login(@RequestParam("email") String email) {
        return authenticationService.requestOtp(email);
    }

    @PostMapping("/verify-code")
    public VerifyOtpResponse verifyCode(@RequestParam("email") String email, @RequestParam("code") String code) {
        return authenticationService.verifyOtp(email, code);
    }

    @PostMapping("/refresh")
    public VerifyOtpResponse refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authenticationService.refreshToken(refreshTokenRequest);
    }

    @GetMapping("/check")
    public String checkToken() {
        return authenticationService.checkToken();
    }

}
