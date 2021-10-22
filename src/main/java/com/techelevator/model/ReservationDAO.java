package com.techelevator.model;

import java.util.List;

public interface ReservationDAO {

    List<Space> available();

    public Reservation createReservation(Reservation newReservation);



}
