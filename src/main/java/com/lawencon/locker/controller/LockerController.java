package com.lawencon.locker.controller;

import com.lawencon.locker.model.Lockers;
import com.lawencon.locker.model.Users;
import com.lawencon.locker.service.LockerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lockers")
@RequiredArgsConstructor
public class LockerController {
    private final LockerService lockerService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Lockers>> getLockersByUser(@PathVariable Long userId) {
        Users user = new Users();
        user.setUserId(userId);
        List<Lockers> lockers = lockerService.getLockersByUser(user);
        return ResponseEntity.ok(lockers);
    }

    @PostMapping("/book")
    public ResponseEntity<String> bookLocker(@RequestBody Lockers locker) {
        Lockers bookedLocker = lockerService.bookLocker(locker);
        return ResponseEntity.ok("Locker booked successfully with ID: " + bookedLocker.getLockerId());
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnLocker(@RequestBody Lockers locker) {
        Lockers returnedLocker = lockerService.returnLocker(locker);
        return ResponseEntity.ok("Locker returned successfully with ID: " + returnedLocker.getLockerId());
    }

    @PostMapping("/open")
    public ResponseEntity<String> openLocker(@RequestParam String password) {
        Lockers locker = lockerService.openLocker(password);
        return ResponseEntity.ok("Locker opened successfully with ID: " + locker.getLockerId());
    }
}
