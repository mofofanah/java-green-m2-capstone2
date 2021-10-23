package com.techelevator;

import javax.sql.DataSource;

import com.techelevator.model.*;
import com.techelevator.view.VenueMenu;
import org.apache.commons.dbcp2.BasicDataSource;

import java.awt.*;
import java.util.List;

public class ExcelsiorCLI {

	private static final String LIST_VENUES = "1"; // when the user selects 1 they are shown a list of all venues
	private static final String SEARCH_HOMES_BY_MLS = "2";
	private static final String ADD_HOME = "3";
	private static final String DELETE_HOME = "4";
	private static final String LIST_AGENT_SUBMENU_OPTIONS = "5";
	private static final String QUIT_PROGRAM = "Q"; //when user presses "Q" the program will terminate.


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
			System.out.println("You chose " + choice);  // TODO:  delete me

			if (choice.equals(LIST_VENUES)) {

				//TODO: implement me using DAO
				handleVenueSubmenu();

			} else if (choice.equals(SEARCH_HOMES_BY_MLS)) {

				//call the menu to ask the user for a MLS Number..


			} else if (choice.equals(QUIT_PROGRAM)) {

				break;  // this allows us to exit the loop

			} else {

				menu.printMessage(choice + " is not a valid option!");

			}


		}  // end of main while loop


		//exit the program
		System.out.println("Exiting the program.... Goodbye!");

	}

	private void handleVenueSubmenu() {

		while (true) {

			List<String> listOfVenues = venueDAO.retrieveAllVenues();
			menu.printListOfVenues(listOfVenues);
			String subMenuChoice = menu.printVenueSubMenu();
			Venue venue = new Venue();
			//List<String> venueDetails = venueDAO.retrieveVenueDetails();

			if (subMenuChoice.equalsIgnoreCase("R")) {
				break;


			} else {

				Venue venueDetails = venueDAO.retrieveVenueDetailsById(Long.parseLong(subMenuChoice));

				System.out.println(venueDetails.getId());


				{

					menu.printMessage(subMenuChoice + " is not a valid option!");


				}
			}
		}
	}
}