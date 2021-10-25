package com.techelevator.model;

import java.util.List;

public interface ReservationDAO {

    List<Reservation> availableSpaces();

    public Reservation createReservation(Reservation newReservation);



}
