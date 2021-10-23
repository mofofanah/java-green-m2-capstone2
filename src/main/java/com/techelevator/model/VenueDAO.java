package com.techelevator.model;

import java.util.List;

public interface VenueDAO {




    public List<String> retrieveAllVenues();
    public Venue retrieveVenueDetailsById(long id);



    

}
