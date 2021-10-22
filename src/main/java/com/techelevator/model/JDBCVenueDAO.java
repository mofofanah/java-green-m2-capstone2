package com.techelevator.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JDBCVenueDAO implements VenueDAO {

    private JdbcTemplate jdbcTemplate;


    public JDBCVenueDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<String> retrieveAllVenues() {
        List<String> venueNames = new ArrayList<>();
        //List<Venue> venues = new ArrayList<>();

        String sql = "SELECT * " + "FROM venue " + "ORDER BY name;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        int count = 1;

        while (results.next()) {
            //take the items out of the results and put into a home object
            Venue venue = mapRowToVenue(results);
            //add home object to our list
            venueNames.add(count++ + ") " + venue.getName());
           // venues.add(venue);
        }


        return venueNames;




    }

    @Override
    public List<String>  retrieveVenueDetails() {
        Venue venue = null;
       String sql =  "SELECT venue.name, venue.description, city.name, city.state_abbreviation, category.name AS details " + "FROM venue " +

        "JOIN category_venue ON category_venue.venue_id = venue.id " +

        "JOIN category ON category.id = category_venue.category_id " +
               "JOIN city ON city.id = venue.city_id " +
               "WHERE city_id = 1 AND venue_id = 1;";

       

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        List<String> venueDetails = new ArrayList<>();

        while (results.next()) {



            venue = mapRowToVenue(results);

            String details = venue.getName() + " " + venue.getDescription() + " " + venue.getCityName()+ " " + venue.getStateName();
            venueDetails.add(details);


            return venueDetails;



        }


        //SELECT * FROM city

        //WHERE id = 1



        return null;
    }






    private Venue mapRowToVenue(SqlRowSet results) {

        Venue venue = new Venue();

        venue.setId(results.getLong("id"));
        venue.setName(results.getString("name"));
        venue.setDescription(results.getString("description"));
        venue.setCityName(results.getString("city_id"));


        return venue;
    }

    private int retrieveNextVenueId() {
        SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('venue_id_seq')");
        if(nextIdResult.next()) {
            return nextIdResult.getInt(1);
        } else {
            throw new RuntimeException("Something went wrong while getting an id for the new Venue");
        }
    }

}


