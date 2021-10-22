package com.techelevator.model;

import java.util.List;

public interface ReservationDAO {

    List<String> availableSpaces();

    public Reservation createReservation(Reservation newReservation);



}
