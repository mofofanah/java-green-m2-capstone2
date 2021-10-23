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

        SqlRowSet results = jdbcTemplate.queryForRowSet(availableSpaceSql, retrieveNextSpaceId(), retrieveNextVenueId() );

        while (results.next()) {


            Space spaces = mapRowToSpace(results);
            Reservation reservation = mapRowToReservation(results);
            String idToString = Long.toString(spaces.getId());

            String availableSpace = idToString + "" + spaces.getName() + "" + spaces.getDailyRate() + "" + spaces.getMaxOccupancy() +
                    "" + spaces.isAccessible() + "" + 1000;
            availableSpaces.add(availableSpace);




        }

        return availableSpaces;





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

    private int retrieveNextVenueId() {
        SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('venue_id_seq')");
        if(nextIdResult.next()) {
            return nextIdResult.getInt(1);
        } else {
            throw new RuntimeException("Something went wrong while getting an id for the new Venue");
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
