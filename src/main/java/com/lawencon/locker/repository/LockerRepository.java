package com.lawencon.locker.repository;

import com.lawencon.locker.model.Lockers;
import com.lawencon.locker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LockerRepository extends JpaRepository<Lockers, Long> {
    List<Lockers> findByUser(Users user);
    Lockers findByPassword(String password);
    Lockers findByLockerId(Long lockerId);
}
