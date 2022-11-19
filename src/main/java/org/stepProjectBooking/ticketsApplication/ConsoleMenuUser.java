package org.stepProjectBooking.ticketsApplication;


import org.stepProjectBooking.ticketsApplication.DAO.tripDAO.TripController;
import org.stepProjectBooking.ticketsApplication.DAO.tripDAO.TripService;
import org.stepProjectBooking.ticketsApplication.trips.Trip;
import org.stepProjectBooking.ticketsApplication.trips.TripCreator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ConsoleMenuUser {

    Scanner scanner =  new Scanner(System.in);
    private static final Map<String, Runnable> consoleMenuDispatcher = new HashMap<>();

    private final int TIME_BEFORE_FLIGHT_BLOCK_BOOKING = 2;
    Runnable runnable;
    String regex = "^[A-Z][a-zA-z ]{1,29}$"; // regular expression for name and surname

    List<Trip> trips =  new ArrayList<>();
    TripCreator tripCreator = new TripCreator();

    TripController tripController = new TripController();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM \uD83D\uDEEB HH:mm");
    private void fillTripsTraining (){
        for (int i = 0; i < 50; i++) {
           trips.add(tripCreator.createTrip());
        }
    }

    public ConsoleMenuUser() {
        consoleMenuDispatcher.put("1", this :: showTrips24Hours);
        consoleMenuDispatcher.put("2", this :: showInfoTrip);
        consoleMenuDispatcher.put("3", this :: searchAndBooking);
        consoleMenuDispatcher.put("4", this :: cancelBooking);
        consoleMenuDispatcher.put("5", this :: showMyTrips);
        consoleMenuDispatcher.put("exit", this :: exitApplication);
    }

//    try {
//        familyController.downloadData();
//        counterOfFamilies = familyController.getAllFamilies().size() - 1;
//    } catch (
//    FileNotFoundException e) {
//        System.out.println("Attention! Database not found!");
//
//    }

    public void run() {
        fillTripsTraining();

        System.out.println("AviaDreamer greets you!\n" +
                "Welcome on board!\n" +
                "We are happy to help you enjoying your trip!\n");
        boolean isContinue = true;
        String choice;
        while (isContinue) {
            showMainMenu();
            System.out.println("Please choose the number of your action and print it and enter!");
            if (scanner.hasNextInt(consoleMenuDispatcher.size() + 1) || scanner.hasNext("exit")) {
                choice = scanner.next().toLowerCase();
                runnable = consoleMenuDispatcher.get(choice);
                runnable.run();
            } else {
                System.out.println("Wrong input");
                scanner.next();
            }
        }
    }

    private void showMainMenu() {

        System.out.println("1: Show all trips available for 24 hours");
        System.out.println("2: Show information about the chosen trip (remember Trip ID)");
        System.out.println("3: Search and book your cosy trip");
        System.out.println("4: Cancel booking");
        System.out.println("5: My trips");
        System.out.println("Exit: Leave application ");

    }


    private void exitApplication() {
        System.out.println("Thank you for being with AviaDreamer!\n" +
                "We would love to see you next time!");
        System.exit (0);

    }

    private void showMyTrips() {


    }

    private void cancelBooking() {
    }

    private void searchAndBooking() {
    }

    private void showInfoTrip() {
        boolean isContinue = true;


    }

    private void showTrips24Hours() {
        //Use TreeMap to sort updated trips by DateTime
        Map <LocalDateTime, Trip> sortedByDateTimeListTrip= new TreeMap<>();



        for (Trip trip:
                tripController.downLoadAllTrips()) {
            sortedByDateTimeListTrip.put(actualize(trip.getTimeTrip()), trip);
        }

        for (Map.Entry<LocalDateTime, Trip> trip :
            sortedByDateTimeListTrip.entrySet()) {
            showActualized24HTrip(trip.getValue());
        }
    }

    /*Method actualize departure time adding actual date within 24 hours + 2 hours (the time before user cannot buy tickets)*/
    private LocalDateTime actualize(LocalTime departureTime) {

        if (departureTime.isAfter(LocalTime.now().plusHours(TIME_BEFORE_FLIGHT_BLOCK_BOOKING))) {
            return LocalDateTime.of(LocalDate.now(),departureTime);
        } else return LocalDateTime.of(LocalDate.now().plusDays(1), departureTime);
    }

    /*Method showTrip with actualized date*/
    private void showActualized24HTrip (Trip trip) {
        String departureTime = dateTimeFormatter.format(actualize(trip.getTimeTrip()));
        System.out.println(String.format(" %5s  %s - %-10s  %s",
                trip.getTripId(), trip.getDeparture(), trip.getDestination(), departureTime));
    }
}

