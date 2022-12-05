package org.stepProjectBooking.ticketsApplication;


import org.stepProjectBooking.ticketsApplication.DAO.bookingDAO.BookingController;
import org.stepProjectBooking.ticketsApplication.DAO.tripDAO.TripController;

import org.stepProjectBooking.ticketsApplication.trips.Booking;
import org.stepProjectBooking.ticketsApplication.trips.Destinations;
import org.stepProjectBooking.ticketsApplication.trips.Trip;
import org.stepProjectBooking.ticketsApplication.trips.TripBooking;
import org.stepProjectBooking.ticketsApplication.user.Passenger;
import org.stepProjectBooking.ticketsApplication.user.Purchaser;
import org.stepProjectBooking.ticketsApplication.user.User;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;


public class ConsoleMenuUser {

    Scanner scanner =  new Scanner(System.in);
    private static final Map<String, Runnable> consoleMenuDispatcher = new HashMap<>();

    private static final int TIME_BEFORE_FLIGHT_BLOCK_BOOKING = 0;


    private static final int TIME_BOOKING_IN_ADVANCE = 2;

    private static final int MAX_RESERVE_PLACES_PER_TRIP = 100;
    Runnable runnable;
    final String regexName = "^[A-Z][a-zA-z ]{1,29}$"; // regular expression for name and surname

    TripController tripController = new TripController();
    BookingController bookingController = new BookingController();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM \uD83D\uDEEB HH:mm");

    public ConsoleMenuUser() {
        consoleMenuDispatcher.put("1", this :: showTrips24Hours);
        consoleMenuDispatcher.put("2", this :: showInfoTrip);
        consoleMenuDispatcher.put("3", this :: searchAndBooking);
        consoleMenuDispatcher.put("4", this :: cancelBooking);
        consoleMenuDispatcher.put("5", this :: showMyTrips);
        consoleMenuDispatcher.put("6", this :: exitApplication);
    }

    public void run() {

        System.out.println("""
                AviaDreamer greets you!
                Welcome on board!
                We are happy to help you enjoying your trip!
                """);

        String choice;
        while (true) {
            showMainMenu();
            if (scanner.hasNextInt(consoleMenuDispatcher.size() + 1))  {
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
        System.out.println("6: Leave application");

    }


    private void exitApplication() {
        System.out.println("Thank you for being with AviaDreamer!\n" +
                "We would love to see you next time!");
        bookingController.saveTripBookingList(bookingController.getTripBookingList());
        System.exit (0);

    }

    private void showMyTrips() {

        Purchaser purchaser = new Purchaser();

        System.out.println("Please enter your name and surname!");

        purchaser = (Purchaser) getNameAndSurnameUser(purchaser);


        for (Booking booking : bookingController.getBookingByNameSurname(purchaser)) {
            showBooking(booking);
        }

    }

    private void cancelBooking() {


            int temp;
 OUTER :    while (true) {

            System.out.println("""
                    Do you want to cancel booking!
                    Please enter booking ID!
                    If you want to leave this mode please enter '0'!""");


             boolean isNotCorrect = true;
             do {
                 String tempString;

                 if (Pattern.matches("^\\d{0,11}$", tempString = scanner.next())) {

                     temp = Integer.parseInt(tempString);
                     if (temp == 0) {
                         break OUTER;
                     } else {
                         if (bookingController.deleteBookingById(temp)) {
                             System.out.println("Your booking " + temp + " deleted successfully");
                             isNotCorrect = false;
                         } else System.out.println("Wrong input!\nPlease try again!");
                     }

                 }
             } while (isNotCorrect);

             // method to delete Booking

        }

    }

    /*Method to search available trips based in destination, date, required number of places
    * with method 'chooseTrip' and propose to make booking calling method 'makeBooking'*/

    private void searchAndBooking() {
        boolean isContinue = true;
        Destinations destination;
        LocalDate dateDeparture;
        int reserveNum = 0;
        String reserveNumString;
        List <Trip> availableTrips;
        Trip bookingTrip = null;
        destination = getDestinationFromUser();
        System.out.println("You have chosen destination " + destination);
        dateDeparture = getDateDepartureFromUser();
        System.out.println("You have chosen departure date " + dateDeparture);

        do {
            System.out.println("Please enter the number places you need for booking");

            if (Pattern.matches("^([1-9](\\d)?)|([0-9]\\d)$", reserveNumString = scanner.next())) {

                reserveNum = Integer.parseInt(reserveNumString);
                if (reserveNum <= MAX_RESERVE_PLACES_PER_TRIP) {                      // if there is possibility to get available places by Trip and Date
                    isContinue = false;
                } else System.out.println("Wrong input!\n" +
                        "Please enter number no more than " + MAX_RESERVE_PLACES_PER_TRIP);
            } else System.out.println("Wrong input!");
        } while (isContinue);

           availableTrips = bookingController.getAvailableTrips (destination, dateDeparture,reserveNum); // get list of available trips for the requested information
           if (availableTrips.size() != 0) {
               bookingTrip = chooseTrip(availableTrips);
           } else System.out.println("No available trips to " + destination + " on " + dateDeparture);
           

         if (bookingTrip != null) {
             makeBooking(bookingTrip, dateDeparture);
         } else System.out.println("Thank you being with AviaDreamer!");


    }

    private LocalDate getDateDepartureFromUser() {
        LocalDate dateDeparture;
        int day = 0;
        int month = 0;
        String monthString;
        String dayString;
        while (true) {

            System.out.println("Please your date departure");
                    boolean isNotCorrect = true;

            do {
                System.out.println("Please enter month");

                if (Pattern.matches("^([1-9](\\d)?)|([0-9]\\d)$", monthString = scanner.next())) {

                    month = Integer.parseInt(monthString);
                    if (month <= 12 && month > 0) {
                        isNotCorrect = false;
                    } else  System.out.println("The month is out of bound");
                } else System.out.println("Wrong input!\nPlease try again!");
            } while (isNotCorrect);

            isNotCorrect = true;

            do {
                System.out.println("Please enter day");
                

                if (Pattern.matches("^([1-9](\\d)?)|([0-9]\\d)$", dayString = scanner.next())) {

                    day = Integer.parseInt(dayString);
                    switch (month) {
                        case 1, 3, 5, 7, 8, 10, 12 -> {
                            if (day <= 31 && day > 0) {
                                isNotCorrect = false;
                            } else System.out.println("The day is out of bound");
                        }
                        case 2 -> {
                            if (day <= 28 && day > 0) {
                                isNotCorrect = false;
                            } else System.out.println("The day is out of bound");
                        }
                        default -> {
                            if (day <= 30 && day > 0) {
                                isNotCorrect = false;
                            } else System.out.println("The day is out of bound");
                        }
                    }
                    
                    
                } else System.out.println("Wrong input!\nPlease try again!");
                
               

            } while (isNotCorrect);

            try {
                dateDeparture = LocalDate.of (Year.now().getValue(),month,day);
            } catch (DateTimeException e) {
                System.out.println("Wrong input!\nPlease try again!");
                continue;
            }

            if (dateDeparture.isBefore(LocalDate.now().plusDays(TIME_BOOKING_IN_ADVANCE))
                    && dateDeparture.isAfter(LocalDate.now().minusDays(1))) {
                return dateDeparture;

            } else System.out.println("Your date is out of time for reservation ");
        }

    }

    private Destinations getDestinationFromUser() {
        Destinations destination;
        while (true) {

            System.out.println("Please enter destination!");
            String destinationName = scanner.next().trim().toUpperCase();
                    for (Trip trip : tripController.downLoadAllTrips()) {
                        try {
                            if (trip.getDestination().equals(Destinations.valueOf(destinationName))) {
                                destination = Destinations.valueOf(destinationName);
                                return destination;
                            }
                        } catch (IllegalArgumentException e) {
                            break;
                        }
                    } System.out.println("Wrong input! Please try again!");
                }


        }


    /*Method get list of available trips and return chosen by user trip
     according to the sequential number for continue to book places on this trip*/
         private Trip    chooseTrip(List <Trip> tripList) {
              Map <Integer, Trip> availableTripsMap = new HashMap<>();
              Trip chosenTrip;
              int count = 1;
              int choice;
              String choiceString;
             for (Trip trip:
                  tripList) {
                 availableTripsMap.put (count++, trip);
             }

             for (Map.Entry <Integer, Trip> trip:
                  availableTripsMap.entrySet()) {
                  System.out.printf ("%d: %s\n", trip.getKey(), trip.getValue().prettyFormat());
             }

             boolean isNotCorrect = true;
             do {

                 System.out.println("Please chose number of trip");

                 if (Pattern.matches("^([1-9](\\d)?)|([0-9]\\d)$", choiceString = scanner.next())) {

                     choice = Integer.parseInt(choiceString);
                     if (choice < tripList.size() + 1) {
                         chosenTrip = availableTripsMap.get(choice);
                         isNotCorrect = false;
                         System.out.println("You have chosen trip: " + chosenTrip.prettyFormat());
                         return chosenTrip;
                     } else  System.out.println("Number is out of bound");
                 } else System.out.println("Wrong input!\nPlease try again!");
             } while (isNotCorrect);
             return null;
         }

    /*Method allows to book places entering Purchaser's name and surname,
          and Passengers' name and surname*/
         private void makeBooking (Trip bookingTrip, LocalDate dateDeparture) {
             Purchaser purchaser = new Purchaser();
             List <Passenger> passengerList = new ArrayList<>();
             boolean isContinue = true;
             int reserveNum = 0;
             String reserveNumString;
             int bookingId;


          while (isContinue) {

             System.out.println("If you want to continue booking enter any symbol!\n" +
                     "If you want to leave this mode please enter 'exit'!");

             String choice = scanner.next().trim().toLowerCase();
             if ("exit".equals(choice)) {
                 break;
             } else {
                 System.out.println("Please enter your name and surname");

                 purchaser = (Purchaser) getNameAndSurnameUser(purchaser);

                 isContinue = true;
                 do {
                     System.out.println("Please enter the number places you need for booking");

                     if (Pattern.matches("^([1-9](\\d)?)|([0-9]\\d)$", reserveNumString = scanner.next())) {

                         reserveNum = Integer.parseInt(reserveNumString);
                         if (reserveNum <= MAX_RESERVE_PLACES_PER_TRIP) {                      // if there is possibility to get available places by Trip and Date
                             isContinue = false;
                         } else System.out.println("Wrong input!\n" +
                                 "Please enter number no more than " + MAX_RESERVE_PLACES_PER_TRIP);
                     } else System.out.println("Wrong input!");
                 } while (isContinue);

                 System.out.println("Please enter passengers' name and surname, " +

                         "including your credentials if you are passenger! ");


                 while (reserveNum > 0) {
                     Passenger passenger = new Passenger();
                     passengerList.add((Passenger) getNameAndSurnameUser(passenger));
                     reserveNum--;
                 }

                 System.out.println("Do you want to finalize your booking?\n" +
                         "Enter yes or no");

                 choice = scanner.next().trim().toLowerCase();

                 if (choice.equals("yes")) {
                     System.out.println("Create booking");
                     // transfer data to Booking.Controller
                     bookingId = bookingController.createNewBooking(purchaser, bookingTrip, passengerList, dateDeparture);
                     System.out.println("Your booked \n" + bookingTrip.prettyFormat() +
                             " on " + dateDeparture +
                             " booking ID: " + bookingId +
                             "\nThank you being with AviaDreamer!");
                 } else {
                     System.out.println("You cancelled process of booking! \n" +
                             "Thank you being with AviaDreamer!");
                 }
             }

         }

         }


        /*Method read name and surname, check them by regular expression and return User*/
         private User getNameAndSurnameUser(User user) {

             String nameTemp;

             boolean isNotCorrect = true;
             do {
                 System.out.println("Please enter name");
                 if (Pattern.matches(regexName, nameTemp = scanner.next())) {
                     user.setName(nameTemp);
                     isNotCorrect = false;
                 } else System.out.println("Wrong input!\nPlease try again!");
             } while (isNotCorrect);

             isNotCorrect = true;

             do {
                 System.out.println("Please enter surname");
                 if (Pattern.matches(regexName, nameTemp = scanner.next())) {
                     user.setSurname(nameTemp);
                     isNotCorrect = false;
                 } else System.out.println("Wrong input!\nPlease try again!");
             } while (isNotCorrect);

             return user;
         }

    private void showInfoTrip() {

 OUTER:     while (true) {
            System.out.println("Please enter trip ID you have chosen!\n" +
                    "If you want to leave this mode please enter exit");
            String tripId = scanner.next().toUpperCase().trim();
            if ("exit".equalsIgnoreCase(tripId)) {
                 break;
             } else {

                 for (Trip trip : tripController.downLoadAllTrips()) {
                     if (trip.getTripId().equals(tripId)) {
                         showTripBooking(bookingController.getTripInfoById(tripId));
                         break OUTER;
                     }
                 } System.out.println("Wrong input! Please try again!");

             }
              }

    }

    private void showTrips24Hours () {

               tripController.downLoadAllTrips()
                     .stream()
                     .sorted((trip1, trip2) -> actualize(trip1.getTimeTrip()).compareTo(actualize(trip2.getTimeTrip())))

                     .forEach(this::showActualized24HTrip);


    }

    /*Method actualize departure time adding actual date within 24 hours + 2 hours (the time before user cannot buy tickets)*/
    private LocalDateTime actualize(LocalTime departureTime) {

        if (departureTime.
                isAfter(LocalTime.now().plusHours(TIME_BEFORE_FLIGHT_BLOCK_BOOKING))) {
            return LocalDateTime.of(LocalDate.now(),departureTime);
        } else return LocalDateTime.of(LocalDate.now().plusDays(1), departureTime);
    }

    /*Method showTrip with actualized date*/
    private void showActualized24HTrip (Trip trip) {
        String departureTime = dateTimeFormatter.format(actualize(trip.getTimeTrip()));
        System.out.printf("%5s  %s - %-10s  %s\n",
                trip.getTripId(),
                trip.getDeparture(),
                trip.getDestination(),
                departureTime);
    }
    private void showTripBooking (TripBooking tripBooking) {

        System.out.printf("%s %s %s - %-10s  available seats: %d\n",
                tripBooking.getTrip().getTripId(),
                dateTimeFormatter.format(tripBooking.getDate()),
                tripBooking.getTrip().getDeparture(),
                tripBooking.getTrip().getDestination(),
                tripBooking.getFreePlace());
    }

    private void showBooking (Booking booking) {

        System.out.printf("%-10d  %-6s %s - %-10s %s\n",

                booking.getIdBooking(),
                booking.getTrip().getTripId(),
                booking.getTrip().getDeparture(),
                booking.getTrip().getDestination(),
                dateTimeFormatter.format (actualize(booking.getTrip().getTimeTrip())));
    }
}

