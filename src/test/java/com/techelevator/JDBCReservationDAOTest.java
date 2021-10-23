package com.techelevator;

import com.techelevator.model.JDBCReservationDAO;
import com.techelevator.model.JDBCSpaceDAO;
import com.techelevator.model.JDBCVenueDAO;
import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JDBCReservationDAOTest  extends DAOIntegrationTest {

    // dO a @before for
    private JDBCReservationDAO reservationDAO;
    private JDBCSpaceDAO spaceDAO;
    private JDBCVenueDAO venueDAO;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setup() {
        //String sqlInsertCountry = "INSERT INTO country (code, name, continent, region, surfacearea, indepyear, population, lifeexpectancy, gnp, gnpold, localname, governmentform, headofstate, capital, code2) VALUES (?, 'Afghanistan', 'Asia', 'Southern and Central Asia', 652090, 1919, 22720000, 45.9000015, 5976.00, NULL, 'Afganistan/Afqanestan', 'Islamic Emirate', 'Mohammad Omar', 1, 'AF')";
        jdbcTemplate = new JdbcTemplate(getDataSource());
        //jdbcTemplate.update(sqlInsertCountry, TEST_COUNTRY);
        reservationDAO = new JDBCReservationDAO(getDataSource());


    }

    List<String> availableSpaces= reservationDAO.availableSpaces();


}