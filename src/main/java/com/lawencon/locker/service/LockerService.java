package com.lawencon.locker.service;

import com.lawencon.locker.model.Lockers;
import com.lawencon.locker.model.Users;
import com.lawencon.locker.repository.LockerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LockerService {
    private final LockerRepository lockerRepository;


    public List<Lockers> getLockersByUser(Users user) {
        return lockerRepository.findByUser(user);
    }

    public Lockers bookLocker(Lockers locker) {
        // Mengecek apakah locker sudah dipesan
        if (locker.isBooked()) {
            throw new RuntimeException("Locker is already booked");
        }

        // Mengecek jumlah locker yang sudah dipesan oleh user
        List<Lockers> userLockers = getLockersByUser(locker.getUser());
        if (userLockers.size() >= 3) {
            throw new RuntimeException("User has already booked maximum number of lockers");
        }

        // Menghitung deposit sesuai dengan jumlah hari peminjaman
        long rentalDays = ChronoUnit.DAYS.between(locker.getBookingDate(), locker.getReturnDate());
        double deposit = rentalDays * 10000;

        locker.setDeposit(deposit);
        locker.setBooked(true);

        return lockerRepository.save(locker);
    }

    public Lockers returnLocker(Lockers locker) {
        // Mengecek apakah locker sudah dipesan
        if (!locker.isBooked()) {
            throw new RuntimeException("Locker is not booked");
        }

        // Mengecek apakah denda sudah dikenakan
        if (locker.isPenaltyApplied()) {
            locker.setDeposit(0); // Deposit hangus
        } else {
            // Menghitung denda jika pengembalian terlambat
            long rentalDays = ChronoUnit.DAYS.between(locker.getBookingDate(), ZonedDateTime.now());
            if (rentalDays > 1) {
                double penalty = (rentalDays - 1) * 5000;
                double newDeposit = locker.getDeposit() - penalty;
                if (newDeposit <= 0) {
                    throw new RuntimeException("Deposit insufficient to cover penalty");
                }
                locker.setDeposit(newDeposit);
                locker.setPenaltyApplied(true);
            }
        }

        locker.setBooked(false);
        return lockerRepository.save(locker);
    }

    public Lockers openLocker(String password) {
        Lockers locker = lockerRepository.findByPassword(password);
        if (locker == null) {
            throw new RuntimeException("Invalid password");
        }

        // Mengecek apakah password sudah digunakan lebih dari 2 kali
        if (locker.getPasswordUsageCount() >= 2) {
            throw new RuntimeException("Password usage limit exceeded");
        }

        // Mengecek apakah password benar
        if (!locker.isBooked()) {
            throw new RuntimeException("Locker is not booked");
        }

        locker.incrementPasswordUsageCount();
        return lockerRepository.save(locker);
    }
}
