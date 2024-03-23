package com.lawencon.locker.model;

import jakarta.persistence.*;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Lockers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lockerId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    private String password;
    private boolean isBooked;
    private ZonedDateTime bookingDate;
    private ZonedDateTime returnDate;
    private double deposit;
    private boolean isPenaltyApplied;
    private int passwordUsageCount;

    public void incrementPasswordUsageCount() {
        this.passwordUsageCount++;
    }
}
