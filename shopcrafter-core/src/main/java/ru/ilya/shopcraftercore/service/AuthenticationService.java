package ru.ilya.shopcraftercore.service;

import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.ilya.shopcraftercore.dto.OtpResponse;
import org.springframework.stereotype.Service;
import ru.ilya.shopcraftercore.dto.RefreshTokenRequest;
import ru.ilya.shopcraftercore.dto.VerifyOtpResponse;
import ru.ilya.shopcraftercore.entity.Role;
import ru.ilya.shopcraftercore.entity.User;
import ru.ilya.shopcraftercore.exception.EmailValidationException;
import ru.ilya.shopcraftercore.repository.UserRepository;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final OtpService otpService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    public AuthenticationService(
            OtpService otpService,
            JwtService jwtService,
            UserRepository userRepository,
            UserDetailsService userDetailsService) {
        this.otpService = otpService;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    public OtpResponse requestOtp(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setRole(Role.USER);
            userRepository.save(newUser);
        }
        boolean sent = otpService.sendOtp(email);
        if (!sent)
            throw new EmailValidationException();
        OtpResponse otpResponse = new OtpResponse();
        otpResponse.setEmail(email);
        otpResponse.setMessage("Письмо с кодом подтверждения отправлено на почту");
        return otpResponse;
    }

    public VerifyOtpResponse verifyOtp(String email, String code) {
        if (!otpService.verifyOtp(email, code))
            throw new BadCredentialsException("Invalid code");

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        String jwtToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        VerifyOtpResponse verifyOtpResponse = new VerifyOtpResponse();
        verifyOtpResponse.setJwtToken(jwtToken);
        verifyOtpResponse.setRefreshToken(refreshToken);
        return verifyOtpResponse;
    }

    public VerifyOtpResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUsername(refreshTokenRequest.getRefreshToken());

        if (userEmail == null)
            throw new JwtException("Refresh token is not valid");

        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

        if (!jwtService.isTokenValid(refreshTokenRequest.getRefreshToken(),
                userDetails))
            throw new JwtException("Refresh token expired");
        String accessToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        VerifyOtpResponse verifyOtpResponse = new VerifyOtpResponse();
        verifyOtpResponse.setJwtToken(accessToken);
        verifyOtpResponse.setRefreshToken(refreshToken);
        return verifyOtpResponse;
    }

    public String checkToken() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return "ok: " + userDetails.getUsername();
    }
}
