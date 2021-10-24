


package com.techelevator.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JDBCSpaceDAO implements SpaceDAO{

    private JdbcTemplate jdbcTemplate;
    private VenueDAO venueDAO;

    public JDBCSpaceDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }









        private Space mapRowToSpace(SqlRowSet results) {

            Space space = new Space();

            space.setId(results.getLong("id"));
            space.setName(results.getString("name"));
            space.setAccessible(results.getBoolean("is_accessible"));
            space.setOpenFrom(results.getInt("open_from"));
            space.setOpenTo(results.getInt("open_to"));
            space.setDailyRate(results.getBigDecimal("daily_rate"));
            space.setMaxOccupancy(results.getInt("max_occupancy"));



            return space;
        }

    private int retrieveNextSpaceId () {
        SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('space_id_seq')");
        if (nextIdResult.next()) {
            return nextIdResult.getInt(1);
        } else {
            throw new RuntimeException("Something went wrong while getting an id for the new Venue");
        }
    }

    private int retrieveNextVenueId() {
        SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('venue_id_seq')");
        if(nextIdResult.next()) {
            return nextIdResult.getInt(1);
        } else {
            throw new RuntimeException("Something went wrong while getting an id for the new Venue");
        }
    }

    @Override
    public List<Space> retrieveVenueSpaces(long id) {


        Space space = null;
        List<Space> spaces = new ArrayList<>();
        // "SELECT id, name, is_accessible, open_from, open_to, cast(daily_rate as decimal), max_occupancy " +
        //   "FROM space;";
        //create a SQL statement
        String sql = "SELECT space.id, space.name, space.is_accessible, space.open_from, space.open_to, cast(space.daily_rate as decimal), space.max_occupancy " +
                "FROM venue " +
                "JOIN space ON space.venue_id = venue.id " +
                "WHERE venue.id = ?;";
        //calling the database and executing our query
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,id);

        //loop through the results, if any.
        while (results.next()) {
            space =  mapRowToSpace(results);
            spaces.add(space);





        }

        return spaces;

    }
}
