package com.techelevator.model;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JDBCSpaceDAO implements SpaceDAO{

    private JdbcTemplate jdbcTemplate;

    public JDBCSpaceDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<Space> retrieveVenueSpaceDetails() {
        return null;
    }
}
