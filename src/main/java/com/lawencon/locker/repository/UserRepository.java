package com.lawencon.locker.repository;

import com.lawencon.locker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByPhoneNumber(String phoneNumber);
    Users findByEmail(String email);
    Users findByKtpNumber(String ktpNumber);
}
