package ru.ilya.shopcrafterapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ilya.shopcraftercore.dto.auth.UserDto;
import ru.ilya.shopcraftercore.dto.goods.store.StoreDto;
import ru.ilya.shopcraftercore.entity.auth.User;
import ru.ilya.shopcraftercore.repository.auth.UserRepository;
import ru.ilya.shopcraftercore.repository.goods.StoreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getCurrentUser(UserDetails userDetails) {
        String email = userDetails.getUsername();
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return UserDto.fromUser(user.get());
    }

    public UserDto updateUser(UserDetails userDetails, UserDto userDto) {
        String email = userDetails.getUsername();
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = userOpt.get();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setRole(userDto.getRole());
        userRepository.save(user);
        return UserDto.fromUser(user);
    }

    public List<StoreDto> getMyStores(UserDetails userDetails) {
        String email = userDetails.getUsername();
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = userOptional.get();
        return user.getStores()
                .stream()
                .map(StoreDto::fromEntity)
                .toList();
    }
}
