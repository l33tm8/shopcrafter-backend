package ru.ilya.shopcrafterapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpService {

    private final StringRedisTemplate stringRedisTemplate;
    private final JavaMailSender mailSender;

    @Value("${otp.expiration-minutes}")
    private int expirationMinutes;

    public OtpService(StringRedisTemplate stringRedisTemplate, JavaMailSender mailSender) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.mailSender = mailSender;
    }

    public boolean sendOtp(String email) {
        String code = generateCode();
        stringRedisTemplate.opsForValue().set(email, code);
        return sendEmail(email, code);
    }

    public boolean verifyOtp(String email, String code) {
        String storedCode = stringRedisTemplate.opsForValue().get(email);

        return storedCode != null && storedCode.equals(code);
    }

    private String generateCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }


    private boolean sendEmail(String to, String code) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setFrom("jeebaden@yandex.ru");
            message.setSubject("Код подтверждения");
            message.setText("Ваш код подтверждения: " + code);
            mailSender.send(message);
            return true;
        }
        catch (MailException e) {
            return false;
        }
    }
}
