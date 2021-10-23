package com.techelevator.view;

import java.util.Scanner;

import java.util.Scanner;
import java.math.BigDecimal;
import java.util.List;
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
            scanner = new Scanner(System.in);
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

    public void printMessage (String message) {
        System.out.println(message);
    }



}


