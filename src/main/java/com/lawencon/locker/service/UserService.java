package com.lawencon.locker.service;

import com.lawencon.locker.model.Users;
import com.lawencon.locker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Users createUser(Users user) {
        Users existingUser = userRepository.findByPhoneNumber(user.getPhoneNumber());
        if (existingUser != null) {
            throw new RuntimeException("User with this phone number already exists");
        }

        existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new RuntimeException("User with this email already exists");
        }

        existingUser = userRepository.findByKtpNumber(user.getKtpNumber());
        if (existingUser != null) {
            throw new RuntimeException("User with this KTP number already exists");
        }

        return userRepository.save(user);
    }
}
