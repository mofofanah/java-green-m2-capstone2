package com.techelevator;

import javax.sql.DataSource;

import com.techelevator.model.*;
import com.techelevator.view.VenueMenu;
import org.apache.commons.dbcp2.BasicDataSource;

import java.awt.*;
import java.util.List;

public class ExcelsiorCLI {

	private static final String LIST_VENUES = "1"; // when the user selects 1 they are shown a list of all venues

	private static final String QUIT_PROGRAM = "Q"; //when user presses "Q" the program will terminate.
	private static final String QUIT_VENUE_SUB_MENU = "R";

	private static final String MAIN_MENU_OPTION_BLUE_NOMAD_OUTPOST = "1";
	private static final String MAIN_MENU_OPTION_CRYSTAL_TRAVELER_TAPROOM = "Crystal Traveler Taproom";
	private static final String MAIN_MENU_OPTION_CURIOUS_ANCHOR_GARAGE = "Curious Anchor Garage";
	private static final String MAIN_MENU_OPTION_FEISTY_BARREL_SALOON = "Feisty Barrel Saloon";
	private static final String MAIN_MENU_OPTION_HIDDEN_OWL_EATERY = "Hidden Owl Eatery";
	private static final String MAIN_MENU_OPTION_HOWLING_POUR_LOUNGE = "Howling Pour Lounge";
	private static final String MAIN_MENU_OPTION_LILAC_BOTTLE_SPEAKEASY = "Lilac Bottle Speakeasy";
	private static final String MAIN_MENU_OPTION_PAINTED_SQUIRREL_CLUB = "Painted Squirrel Club";
	private static final String MAIN_MENU_OPTION_PROUD_LION_HIDEOUT = "Proud Lion Hideout";
	private static final String MAIN_MENU_OPTION_RUNAWAY_TIME_EMPORIUM = "Runaway Time Emporium";
	private static final String MAIN_MENU_OPTION_RUSTY_FARMER_SPOT = "Rusty Farmer Spot";
	private static final String MAIN_MENU_OPTION_SINGING_TABLE_PUB = "Singing Table Pub";
	private static final String MAIN_MENU_OPTION_SMIRKING_STONE_BISTRO = "Smirking Stone Bistro";
	private static final String MAIN_MENU_OPTION_THE_BITTERSWEET_ELEPHANT_TAVERN = "The Bittersweet Elephant Tavern";
	private static final String MAIN_MENU_OPTION_WISE_ROOSTER_BREWHOUSE = "Wise Rooster Brewhouse";




	private static final String[] MAIN_MENU_OPTIONS = new String[] {
			MAIN_MENU_OPTION_BLUE_NOMAD_OUTPOST,
			MAIN_MENU_OPTION_CRYSTAL_TRAVELER_TAPROOM,
			MAIN_MENU_OPTION_CURIOUS_ANCHOR_GARAGE,
			MAIN_MENU_OPTION_FEISTY_BARREL_SALOON,
			MAIN_MENU_OPTION_HIDDEN_OWL_EATERY,
			MAIN_MENU_OPTION_HOWLING_POUR_LOUNGE,
			MAIN_MENU_OPTION_LILAC_BOTTLE_SPEAKEASY,
			MAIN_MENU_OPTION_PAINTED_SQUIRREL_CLUB,
			MAIN_MENU_OPTION_PROUD_LION_HIDEOUT,
			MAIN_MENU_OPTION_RUNAWAY_TIME_EMPORIUM,
			MAIN_MENU_OPTION_RUSTY_FARMER_SPOT,
			MAIN_MENU_OPTION_SINGING_TABLE_PUB,
			MAIN_MENU_OPTION_SMIRKING_STONE_BISTRO,
			MAIN_MENU_OPTION_THE_BITTERSWEET_ELEPHANT_TAVERN,
			MAIN_MENU_OPTION_WISE_ROOSTER_BREWHOUSE };



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
		this.menu = new VenueMenu(System.in, System.out);
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



			} else if (choice.equals(QUIT_VENUE_SUB_MENU)) {

				break;  // this allows us to exit the loop

			} else {
				Venue venueChoice = venueDAO.retrieveVenueDetailsById(Long.parseLong(choice));
				String venueChoiceString = venueChoice.getName();
				menu.printMessage(venueChoiceString);

			}


		}  // end of main while loop


		//exit the program
		System.out.println("Exiting the program.... Goodbye!");

	}

	private void handleVenueSubmenu() {

		while (true) {

			List<String> listOfVenues = venueDAO.retrieveAllVenues();

			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			Venue venue = new Venue();
			//List<String> venueDetails = venueDAO.retrieveVenueDetails();

			if (choice.equals(QUIT_VENUE_SUB_MENU)) {
				break;


			}
			else if (choice.equals(MAIN_MENU_OPTION_BLUE_NOMAD_OUTPOST)) {

				venue = venueDAO.retrieveVenueDetailsById(Integer.parseInt(choice));

				menu.printMessage(venue.getName());



			}

				{






				{

					//menu.printMessage(choice + " is not a valid option!");


				}
			}
		}
	}
}