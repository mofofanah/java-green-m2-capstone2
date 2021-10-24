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
    public List<Venue> retrieveAllVenues() {

        List<Venue> venues = new ArrayList<>();



        String sql = "select venue.id, venue.name, city.name as city, state_abbreviation, description " +
                "from venue " +
                "JOIN city ON city.id = venue.city_id " +
                "JOIN state on state.abbreviation = city.state_abbreviation " +
                "ORDER BY venue.name";



        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        String categorySql = "SELECT category.name " +
                "FROM category_venue " +
                "JOIN category ON category.id = category_venue.category_id " +
                "WHERE category_venue.venue_id = ?;";


        while (results.next()) {
            //take the items out of the results and put into a home object
            Venue venue = mapRowToVenue(results);
            //add home object to our list
            List<String> categoryList = new ArrayList<>();

            SqlRowSet categoryResults = jdbcTemplate.queryForRowSet(categorySql, venue.getId());

            while (categoryResults.next()) {

                String categoryName = categoryResults.getString("name");
                categoryList.add(categoryName);
            }


            venue.setCategories(categoryList);
            venues.add(venue);



        }


        return venues;




    }

    @Override
    public Venue retrieveVenueDetailsById(long id) {

        Venue venue = null;

        String sql = "select venue.id, venue.name, city.name as city, state_abbreviation, description, string_agg(category.name, ', ') as categories " +
                "from venue " +
                "join city on venue.city_id = city.id " +
                "left join category_venue on venue.id = category_venue.venue_id " +
                "left join category on category_venue.category_id = category.id " +
                "where venue.id = ? " +
                "group by venue.id, city.name, city.state_abbreviation;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);

        if (result.next()) {
            venue = mapRowToVenue(result);

        }
        return venue;

    }


/*
    public int retrieveVenueDetailsByIDTest() {
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





        }


        //SELECT * FROM city

        //WHERE id = 1



    }
*/




    private Venue mapRowToVenue(SqlRowSet results) {

        Venue venue = new Venue();

        venue.setId(results.getLong("id"));
        venue.setName(results.getString("name"));
        venue.setDescription(results.getString("description"));
        venue.setCityName(results.getString("city"));
        venue.setStateName(results.getString("state_abbreviation"));


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


