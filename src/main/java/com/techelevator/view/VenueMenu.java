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


        if (venuesToPrint.isEmpty()) {
            System.out.println("No Results Found!");
            return;
        }
        int count = 1;
        for (Venue venue : venuesToPrint) {

            System.out.println(count++ + ") " + venue.getName());
        }
    }

    public void printVenue(Venue venue) {

        if (venue == null) {
            System.out.println("No results found... Pleast try again.");
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


    public void printSpaces (List<Space> spacesToPrint) {

        if (spacesToPrint.isEmpty()) {
            System.out.println("No Results Found!");
            return;
        }
        int count = 1;
        for (Space spaces : spacesToPrint) {

            String details = spaces.getName();
            System.out.println("#" + count++  + " " +  details) ;
        }

        System.out.println("\n*********** *** *** ***  ************\n");
    }
    public String retrieveIdNumberFromUser() {

        System.out.println("\nPlease enter a venue number");
        return scanner.nextLine();

    }




}

