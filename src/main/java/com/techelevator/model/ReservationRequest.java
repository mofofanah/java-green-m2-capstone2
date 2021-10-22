package com.techelevator.model;

import java.time.LocalDate;

public class ReservationRequest {

    private LocalDate reservationDate;
    private int daysReserved;
    private int numberOfAttendees;

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getDaysReserved() {
        return daysReserved;
    }

    public void setDaysReserved(int daysReserved) {
        this.daysReserved = daysReserved;
    }

    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }
}
