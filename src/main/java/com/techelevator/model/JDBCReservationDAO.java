package com.techelevator.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JDBCReservationDAO implements ReservationDAO{

    private JDBCSpaceDAO spaceDAO;
    private JdbcTemplate jdbcTemplate;

    public JDBCReservationDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<String> availableSpaces() {

        LocalDate reserveDate;
        List<String> allSpaces = spaceDAO.retrieveVenueSpaceDetails();
        List<String> availableSpaces = new ArrayList<>();

        String sql = "select reservation_id" "from reservation where(?, ?) overlaps ("start_date, end_date) group by reservation_id



        return null;
    }

    @Override
    public Reservation createReservation(Reservation newReservation) {
        return null;
    }

    private int retrieveNextReservationId() {
        SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('reservation_reservation_id_seq')");
        if(nextIdResult.next()) {
            return nextIdResult.getInt(1);
        } else {
            throw new RuntimeException("Something went wrong while getting an id for the new Reservation");
        }
    }

    private Reservation mapRowToReservation(SqlRowSet results) {

        Reservation reservation = new Reservation();

        reservation.setId(results.getLong("reservation_id"));
        reservation.setNumberOfAttendees(results.getInt("number_of_attendees"));
        reservation.setStartDate(results.getDate("start_date").toLocalDate());
        reservation.setEndDate(results.getDate("end_date").toLocalDate());
        reservation.setReservedFor(results.getString("reserved_for"));

        return reservation;
    }
}
