package com.techelevator.model;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JDBCReservationDAO implements ReservationDAO{


    private JdbcTemplate jdbcTemplate;

    public JDBCReservationDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
