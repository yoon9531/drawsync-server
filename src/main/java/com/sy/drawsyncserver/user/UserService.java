package com.sy.drawsyncserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User signup(String id, String password) {
        if (userRepository.existsById(id)) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        User user = new User(id, password);
        return userRepository.save(user);
    }

    public User login(String id, String password) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return user;
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }
}
