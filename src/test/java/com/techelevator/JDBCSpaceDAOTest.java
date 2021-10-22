package com.techelevator;

import com.techelevator.model.JDBCSpaceDAO;
import com.techelevator.model.JDBCVenueDAO;
import com.techelevator.model.Space;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class JDBCSpaceDAOTest extends DAOIntegrationTest{

    // dO a @before for test
    private JDBCSpaceDAO dao;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setup() {
        //String sqlInsertCountry = "INSERT INTO country (code, name, continent, region, surfacearea, indepyear, population, lifeexpectancy, gnp, gnpold, localname, governmentform, headofstate, capital, code2) VALUES (?, 'Afghanistan', 'Asia', 'Southern and Central Asia', 652090, 1919, 22720000, 45.9000015, 5976.00, NULL, 'Afganistan/Afqanestan', 'Islamic Emirate', 'Mohammad Omar', 1, 'AF')";
        jdbcTemplate = new JdbcTemplate(getDataSource());
        //jdbcTemplate.update(sqlInsertCountry, TEST_COUNTRY);
        dao = new JDBCSpaceDAO(getDataSource());

    }

    @Test
    public void test_to_see_if_all_venue_names_are_retrieved() {

        //insert dummy data into table
        //Arrange
        int nextId = retrieveNextSpaceId();

        List<String> allVenueNamesBeforeInsert = dao.retrieveVenueSpaceDetails();
        String venueSQL = "INSERT INTO venue (id, name, city_id, description) " +
                "VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(venueSQL, nextId, "XYZZY", 1, "Random Description For Test!");

        String venueSQL2 = "INSERT INTO venue (id, name, city_id, description) " +
                "VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(venueSQL, nextId + 1, "ZZZZZ", 1, "Random Description For Test Part Two!");


        //Act
        List<String> allVenueNames = dao.retrieveVenueSpaceDetails(); //method under test
        //Assert

        //Venue venue = dao.retrieveVenueDetails();




        assertNotNull(allVenueNames);
        if (allVenueNamesBeforeInsert.size() + 2 == allVenueNames.size()) {

            assertTrue(true);
        }
        else {
            assertTrue(false);
        }



    }

    private int retrieveNextSpaceId () {
        SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('space_id_seq')");
        if (nextIdResult.next()) {
            return nextIdResult.getInt(1);
        } else {
            throw new RuntimeException("Something went wrong while getting an id for the new Venue");
        }
    }


}
