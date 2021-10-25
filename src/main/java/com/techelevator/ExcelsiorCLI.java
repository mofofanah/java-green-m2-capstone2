package com.techelevator;

import javax.sql.DataSource;

import com.techelevator.model.*;
import com.techelevator.view.VenueMenu;
import org.apache.commons.dbcp2.BasicDataSource;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class ExcelsiorCLI {

	private static final String LIST_VENUES = "1"; // when the user selects 1 they are shown a list of all venues

	private static final String QUIT_PROGRAM = "Q"; //when user presses "Q" the program will terminate.
	private static final String QUIT_VENUE_SUB_MENU = "R";
	private static final String VIEW_SPACES = "1";
	private	static final String SEARCH_FOR_RESERVATION = "2";
	private static final String RESERVE_A_SPACE = "1";





	private VenueMenu menu;
	private VenueDAO venueDAO;
	private SpaceDAO spaceDAO;
	private ReservationDAO reservationDAO;


	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/excelsior_venues");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");


		ExcelsiorCLI application = new ExcelsiorCLI(dataSource);
		application.run();
	}

	public ExcelsiorCLI(DataSource datasource) {
		// create your DAOs here
		this.menu = new VenueMenu();
		venueDAO = new JDBCVenueDAO(datasource);
		spaceDAO = new JDBCSpaceDAO(datasource);
		reservationDAO = new JDBCReservationDAO(datasource);

	}

	public void run() {


		while (true) {

			String choice = menu.printMainMenu();


			if (choice.equals(LIST_VENUES)) {

				//TODO: implement me using DAO
				List<Venue> venueList = venueDAO.retrieveAllVenues();

				//pass venue list to menu method
				menu.printListOfVenues(venueList);
				//have that method return a choice
				//String venueId = menu.retrieveIdNumberFromUser();
				//take option they give us and
				Venue venueFromUserId = venueList.get(Integer.parseInt(choice) - 1);
				//menu.printVenue(venueFromUserId);
				//take printDetails method
				//take them to submenu after this.

				//menu.printVenue(venueFromUserId);

				String venueChoice = menu.userChoice();
				Venue venue = venueList.get(Integer.parseInt(venueChoice) - 1);
				menu.printVenue(venue);

				String subChoice = menu.printVenueSubMenu();

				if (subChoice.equals(VIEW_SPACES)) {

					List<Space> spacesList = spaceDAO.retrieveVenueSpaces(Integer.parseInt(subChoice));
					menu.printListSpaces(spacesList);

					handleVenueSubmenu();;






				} else if (choice.equals(QUIT_PROGRAM)) {

					break;  // this allows us to exit the loop

				} else {


				}


			}  // end of main while loop


			//exit the program
			System.out.println("Exiting the program.... Goodbye!");

		}
	}
	private void handleVenueSubmenu() {

		while (true) {

			String spacesMenuChoice = menu.whatWouldYouLikeToDoNext();

			if (spacesMenuChoice.contains("1")) {
				String reserveASpace = menu.askUserQuestionsToReserveVenueSpace();
				String[] split = reserveASpace.split(", ");
				String date = split[0];
				String daysReserved = split[1];
				String peopleAttending = split[2];
				ReservationRequest reservationRequest = new ReservationRequest();
				LocalDate reservationDate = LocalDate.parse(date);
				reservationRequest.setReservationDate(reservationDate);
				reservationRequest.setDaysReserved(Integer.parseInt(daysReserved));
				reservationRequest.setNumberOfAttendees(Integer.parseInt(peopleAttending));

				List<Reservation> reservationsList = reservationDAO.availableSpaces();
				menu.printAvailableReservations(reservationsList);



			} else if (spacesMenuChoice.equals(QUIT_VENUE_SUB_MENU)) {
				break;
			}


		}


	}
	private String storeUserChoice(String choice) {
		return choice;
	}
}