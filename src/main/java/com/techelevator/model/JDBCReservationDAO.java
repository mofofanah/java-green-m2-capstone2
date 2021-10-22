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

        String reservationStartDate = "10/12/2021";



        LocalDate reserveDate;
        List<String> allSpaces = spaceDAO.retrieveVenueSpaceDetails();
        List<String> availableSpaces = new ArrayList<>();

        String availableSpaceSql = "SELECT space.id, space.venue_id, space.name, space.is_accessible, space.open_from, space.open_to, space.daily_rate::money::numeric::float8, space.max_occupancy  FROM space " +
                "WHERE space.venue_id = ? " +
                "AND space.id NOT IN (SELECT space.id FROM space " +
                "LEFT JOIN reservation ON space.id = reservation.space_id " +
                "WHERE (? <= reservation.end_date " +
                "AND ? >= reservation.start_date) " +
                "OR EXTRACT(MONTH FROM CAST(? AS DATE)) < space.open_from " +
                "OR EXTRACT(MONTH FROM CAST(? AS DATE)) > space.open_to " +
                "OR space.max_occupancy < ?) " +
                "ORDER BY space.daily_rate DESC " +
                "LIMIT 5 ";

        SqlRowSet results = jdbcTemplate.queryForRowSet(availableSpaceSql);

        while (results.next()) {

            Reservation reservation = mapRowToReservation(results);


            int daysReserved = 5;

            LocalDate startDate = reservation.getStartDate();
            LocalDate endDate = startDate.plusDays(daysReserved);
            LocalDate userStartDate = LocalDate.of(2021, 10, 19);
            LocalDate userEndDate =


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
