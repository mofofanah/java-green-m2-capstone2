package com.techelevator.model;

import jdk.vm.ci.meta.Local;
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

        String sql = "SELECT open_from, open_to, reservation.start_date, reservation.end_date " +
                "FROM space " + "JOIN reservation ON reservation.space_id = space.id " +
                "WHERE open_from IS NOT NULL and open_to IS NOT NULL;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {

            Reservation reservation = mapRowToReservation(results);




            LocalDate startDate = reservation.getStartDate();
            String startDateString = startDate.toString();
            LocalDate endDate = reservation.getEndDate();
            LocalDate userStartDate = LocalDate.of(2021, 10, 19);


            if (startDate, endDate, LocalDate.)




            }
        }








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

    public static boolean isOverlapping(re start1, Date end1, Date start2, Date end2) {
        return start1.before(end2) && start2.before(end1);
    }
}
