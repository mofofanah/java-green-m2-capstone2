package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.SQLOutput;
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


    private Scanner scanner;

    public VenueMenu() {

        this.scanner = new Scanner(System.in);
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

    public void printListOfVenues(List<Venue> venuesToPrint) {


        System.out.println("\n*********** Venues ************\n");

        System.out.println("Which Venue Would You Like To View?");
        if (venuesToPrint.isEmpty()) {
            System.out.println("No Results Found!");
            return;
        }
        int count = 1;
        for (Venue venue : venuesToPrint) {

            System.out.println(count++ + ") " + venue.getName());
        }
    }
    public void printAvailableReservations(List<Reservation> reservationsToPrint) {

        if (reservationsToPrint.isEmpty()) {
            System.out.println("No Results Found!");
            return;
        }
        for (Reservation reservation : reservationsToPrint) {
            System.out.println(reservation.toString());
        }

    }

    public void printVenue(Venue venue) {

        if (venue == null) {
            System.out.println("No results found... Please try again.");
            return;
        }

        System.out.println("\n*********** *** Venue Description ***  ************\n");
        System.out.println(venue.getName());
        System.out.println("Location: " + venue.getCityName() + ", " + venue.getStateName());
        System.out.println(venue.getCategories());
        System.out.println();
        System.out.println();

        System.out.println(venue.getDescription());

        System.out.println("\n*********** *** *** ***  ************\n");

    }

    public String printVenueSubMenu() {


        System.out.println("What would like to do next?");
        System.out.println("1) View Spaces");
        System.out.println("2) Search for Reservation");
        System.out.println("R. Return to previous menu\n");

        System.out.println("Please select your choice (number only)");


        String userChoice = scanner.nextLine();
        return userChoice;
    }


    public void printListSpaces (List<Space> spacesToPrint) {

        if (spacesToPrint.isEmpty()) {
            System.out.println("No Results Found!");
            return;
        }
        int count = 1;
        System.out.println(String.format(("%-20s"),"Name" ) + (String.format("%-20s", "open") + (String.format("%-20s","Close") +  String.format("%-20s" ,"Daily Rate") + String.format("%-20s" ,   "Max. Occupancy"))));
        for (Space spaces : spacesToPrint) {

            String details = spaces.getName();

            System.out.println("#" + count++  + " " + String.format("%-18s", spaces.getName())  + String.format("%-18s", spaces.getOpenFrom()) +
                    "   " +String.format("%-18s", spaces.getOpenTo()) + "   $" + String.format("%-18s" , spaces.getDailyRate())  + String.format("%-18s", spaces.getMaxOccupancy()));
        }

        System.out.println("\n*********** *** *** ***  ************\n");
    }



    public String retrieveIdNumberFromUser() {

        System.out.println("\nPlease enter a venue number");
        return scanner.nextLine();

    }
    public String userChoice(){
        System.out.println("What is your choice ?");
        return scanner.nextLine();

    }
    public String whatWouldYouLikeToDoNext() {
        System.out.println("What would you like to do next?");
        System.out.println("1) Reserve a Space");
        System.out.println("R) Return to previous screen");
        return scanner.nextLine();
    }
    public String askUserQuestionsToReserveVenueSpace() {
        System.out.print("When do you need the space?");
        String date = scanner.nextLine();
        System.out.println("How many days will you need the space?");
        int daysToReserve = scanner.nextInt();
        scanner.nextLine();
        System.out.println("How many people will be in attendance?");
        int occupancyAmount = scanner.nextInt();
        scanner.nextLine();

        return date + ", " + daysToReserve + "," + occupancyAmount;


    }
}

