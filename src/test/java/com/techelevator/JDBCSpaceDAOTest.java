package com.techelevator;

import com.techelevator.model.JDBCSpaceDAO;
import com.techelevator.model.JDBCVenueDAO;
import com.techelevator.model.Space;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

import static org.junit.Assert.*;

public class JDBCSpaceDAOTest extends DAOIntegrationTest{

    // dO a @before for test
    private JDBCSpaceDAO dao;
    private JDBCVenueDAO venueDAO;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setup() {
        //String sqlInsertCountry = "INSERT INTO country (code, name, continent, region, surfacearea, indepyear, population, lifeexpectancy, gnp, gnpold, localname, governmentform, headofstate, capital, code2) VALUES (?, 'Afghanistan', 'Asia', 'Southern and Central Asia', 652090, 1919, 22720000, 45.9000015, 5976.00, NULL, 'Afganistan/Afqanestan', 'Islamic Emirate', 'Mohammad Omar', 1, 'AF')";
        jdbcTemplate = new JdbcTemplate(getDataSource());
        //jdbcTemplate.update(sqlInsertCountry, TEST_COUNTRY);
        dao = new JDBCSpaceDAO(getDataSource());

    }

    @Test
    public void test_to_see_if_all_space_details_are_retrieved() {

        //insert dummy data into table
        //Arrange
        int nextId = retrieveNextSpaceId();
        int nextVenueId = retrieveNextVenueId();
        List<String> allSpaceNamesBeforeInsert = dao.retrieveVenueSpaceDetails();


        String venueSQL = "INSERT INTO venue (id, name, city_id, description) " +
                "VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(venueSQL, nextVenueId , "XYZZY", 1, "Random Description For Test!");

        String SpaceSQL = "INSERT INTO space (id, venue_id, name, is_accessible, open_from, open_to, daily_rate, max_occupancy) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(SpaceSQL, nextId, nextVenueId , "XYZZY", true, 1, 8, 1800, 100);

       // String SpaceSQL2 = "INSERT INTO space (id, venue_id, name, is_accessible, open_from, open_to, daily_rate, max_occupancy) " +
        //        "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
      //  jdbcTemplate.update(SpaceSQL2, nextId + 1, nextVenueId,"ZZZZZ", true, 2, 8, 1900, 50);


        //Act
        List<String> allSpaceNames = dao.retrieveVenueSpaceDetails(); //method under test



        assertNotNull(allSpaceNames);
        if (allSpaceNamesBeforeInsert.size() + 1 == allSpaceNames.size()) {

            assertTrue(true);
        }
        else {
            assertTrue(false);
        }
        assertEquals("XYZZY true 1 8 1800.00 100", allSpaceNames.get(allSpaceNames.indexOf("XYZZY true 1 8 1800.00 100")));


        //to test if results are printing out
        //for (String spaces : allSpaceNames) {
           // System.out.println(spaces);
       }








    private int retrieveNextSpaceId () {
        SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('space_id_seq')");
        if (nextIdResult.next()) {
            return nextIdResult.getInt(1);
        } else {
            throw new RuntimeException("Something went wrong while getting an id for the new Venue");
        }
    }

    private int retrieveNextVenueId () {
        SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('venue_id_seq')");
        if (nextIdResult.next()) {
            return nextIdResult.getInt(1);
        } else {
            throw new RuntimeException("Something went wrong while getting an id for the new Venue");
        }
    }





}
