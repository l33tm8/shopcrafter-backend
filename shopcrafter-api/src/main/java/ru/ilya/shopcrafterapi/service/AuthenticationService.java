package ru.ilya.shopcrafterapi.service;

import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.ilya.shopcraftercore.dto.auth.OtpResponse;
import org.springframework.stereotype.Service;
import ru.ilya.shopcraftercore.dto.auth.RefreshTokenRequest;
import ru.ilya.shopcraftercore.dto.auth.VerifyOtpResponse;
import ru.ilya.shopcraftercore.entity.auth.Role;
import ru.ilya.shopcraftercore.entity.auth.User;
import ru.ilya.shopcraftercore.entity.basket.Basket;
import ru.ilya.shopcraftercore.exception.EmailValidationException;
import ru.ilya.shopcraftercore.repository.auth.UserRepository;
import ru.ilya.shopcraftercore.repository.basket.BasketRepository;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final OtpService otpService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final BasketRepository basketRepository;

    public AuthenticationService(
            OtpService otpService,
            JwtService jwtService,
            UserRepository userRepository,
            UserDetailsService userDetailsService,
            BasketRepository basketRepository) {
        this.otpService = otpService;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.basketRepository = basketRepository;
    }

    public OtpResponse requestOtp(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setRole(Role.STORE_ADMIN);
            Basket basket = new Basket();
            basket.setBasketOwner(newUser);
            userRepository.save(newUser);
            basketRepository.save(basket);
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
