package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

import java.util.Scanner;
import java.math.BigDecimal;
import java.util.Scanner;

import com.techelevator.model.JDBCVenueDAO;
import com.techelevator.model.JDBCReservationDAO;
import com.techelevator.model.JDBCSpaceDAO;
import com.techelevator.model.Reservation;
import com.techelevator.model.ReservationDAO;
import com.techelevator.model.Space;
import com.techelevator.model.SpaceDAO;
import com.techelevator.model.Venue;
import com.techelevator.model.VenueDAO;
import com.techelevator.model.ReservationRequest;


public class VenueMenu {


        private PrintWriter out;
        private Scanner scanner;

    public VenueMenu(InputStream input, OutputStream output) {
        this.out = new PrintWriter(output);
        this.scanner = new Scanner(input);
    }



        public String printMainMenu() {

            System.out.println("***************************");
            System.out.println("     Excelsior Venues");
            System.out.println("***************************\n");

            System.out.println("1. List Venues");
            System.out.println("Q. Quit\n");

            System.out.println("Please select your choice (number only)");

            return scanner.nextLine();

        }
    public void printListOfVenues(List<String> venuesToPrint) {


        System.out.println("\n*********** Listing Results ************\n");


        if (venuesToPrint.isEmpty()) {
            System.out.println("No Results Found!");
            return;
        }

        for (String venue : venuesToPrint) {
            System.out.println(venue);
        }
    }

    public void printVenue(Venue venue) {

        if (venue== null) {
            System.out.println("No results found... Pleast try again.");
            return;
        }
        int count = 1;

        if (venue.getId() > 0) {
            System.out.println(venue);
        }



        System.out.println("\n*********** *** *** ***  ************\n");

    }

    public String printVenueSubMenu() {

        System.out.println("***************************");
        System.out.println(" Select Your Venue ");
        System.out.println("***************************\n");

        System.out.println("R. Return to previous menu\n");

        System.out.println("Please select your choice (number only)");


        String venueChoice =scanner.nextLine();
        return venueChoice;
    }

    public void printMessage (String message) {
        System.out.println(message);
    }

    public Object getChoiceFromOptions(Object[] options) {
        Object choice = null;
        while(choice == null) {
            displayMenuOptions(options);
            choice = getChoiceFromUserInput(options);
        }
        return choice;
    }

    private Object getChoiceFromUserInput(Object[] options) {
        Object choice = null;
        String userInput = scanner.nextLine();
        try {
            int selectedOption = Integer.valueOf(userInput);
            if(selectedOption <= options.length) {
                choice = options[selectedOption - 1];
            }
        } catch(NumberFormatException e) {
            // eat the exception, an error message will be displayed below since choice will be null
        }
        if(choice == null) {
            out.println("\n*** "+userInput+" is not a valid option ***\n");
        }
        return choice;
    }

    private void displayMenuOptions(Object[] options) {
        out.println();
        for(int i = 0; i < options.length; i++) {
            int optionNum = i+1;
            out.println(optionNum+") "+options[i]);
        }
        out.print("\nPlease choose an option >>> ");
        out.flush();
    }
}






   // public void printVenueDetails (String )










