package ru.ilya.shopcrafterapi.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.ilya.shopcrafterapi.service.UserService;
import ru.ilya.shopcraftercore.dto.auth.UserDto;
import ru.ilya.shopcraftercore.dto.goods.store.StoreDto;

import java.util.List;

@RestController
@RequestMapping("/users/me")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserDto getMe(@AuthenticationPrincipal UserDetails user) {
        return userService.getCurrentUser(user);
    }

    @PutMapping
    public UserDto updateMe(@AuthenticationPrincipal UserDetails user, @RequestBody UserDto userDto) {
        return userService.updateUser(user, userDto);
    }

    @GetMapping("/stores")
    public List<StoreDto> getStores(@AuthenticationPrincipal UserDetails user) {
        return userService.getMyStores(user);
    }
}
