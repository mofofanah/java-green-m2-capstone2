package com.techelevator.model;

import java.util.List;

public interface VenueDAO {




    public List<Venue> retrieveAllVenues();
    public Venue retrieveVenueDetailsById(long id);



    

}
