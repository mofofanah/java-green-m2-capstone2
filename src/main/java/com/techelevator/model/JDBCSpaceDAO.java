package com.techelevator.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JDBCSpaceDAO implements SpaceDAO{

    private JdbcTemplate jdbcTemplate;

    public JDBCSpaceDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<String> retrieveVenueSpaceDetails() {

        List<String> spaces = new ArrayList<>();

        //create a SQL statement
        String sql = "SELECT * " +
                "FROM space;";
        //calling the database and executing our query
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        //loop through the results, if any.
        while (results.next()) {
            //take the items out of the results and put into a home object
            Space space = mapRowToSpace(results);
            //add home object to our list
            spaces.add(space);



        }
        return spaces;
    }

        private Space mapRowToSpace(SqlRowSet results) {

            Space space = new Space();

            space.setId(results.getLong("id"));
            space.setName(results.getString("name"));
            space.setAccessible(results.getBoolean("true"));
            space.setOpenFrom(results.getInt("int"));
            space.setOpenTo(results.getInt("int"));
            space.setDailyRate(results.getBigDecimal("bigDecimal"));
            space.setMaxOccupancy(results.getInt("int"));


            return space;
        }
}
