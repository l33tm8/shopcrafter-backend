package ru.ilya.shopcraftercore.dto.auth;

import ru.ilya.shopcraftercore.entity.auth.Role;
import ru.ilya.shopcraftercore.entity.auth.User;

public class UserDto {
    private long id;
    private String email;
    private String phone;
    private String name;
    private Role role;

    public static UserDto fromUser(User user) {
        UserDto dto = new UserDto();
        dto.id = user.getId();
        dto.email = user.getEmail();
        dto.phone = user.getPhone();
        dto.name = user.getName();
        dto.role = user.getRole();
        return dto;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }
}
