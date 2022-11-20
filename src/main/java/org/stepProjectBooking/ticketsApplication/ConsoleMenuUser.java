package org.stepProjectBooking.ticketsApplication;


import org.stepProjectBooking.ticketsApplication.DAO.tripDAO.TripController;

import org.stepProjectBooking.ticketsApplication.trips.Destinations;
import org.stepProjectBooking.ticketsApplication.trips.Trip;
import org.stepProjectBooking.ticketsApplication.user.Passenger;
import org.stepProjectBooking.ticketsApplication.user.Purchaser;
import org.stepProjectBooking.ticketsApplication.user.User;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;


public class ConsoleMenuUser {

    Scanner scanner =  new Scanner(System.in);
    private static final Map<String, Runnable> consoleMenuDispatcher = new HashMap<>();


    private static final int TIME_BEFORE_FLIGHT_BLOCK_BOOKING = 2;

    private static final long TIME_BOOKING_IN_ADVANCE = 1;

    private static final int MAX_RESERVE_PLACES_PER_TRIP = 100;
    Runnable runnable;
    final String regexName = "^[A-Z][a-zA-z ]{1,29}$"; // regular expression for name and surname

    final Pattern EXIT_PAT = Pattern.compile("^(?i)exit$");
    List<Trip> trips =  new ArrayList<>();

    TripController tripController = new TripController();
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


        System.out.println("AviaDreamer greets you!\n" +
                "Welcome on board!\n" +
                "We are happy to help you enjoying your trip!\n");
        boolean isContinue = true;
        String choice;
        while (isContinue) {
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
        System.exit (0);

    }

    private void showMyTrips() {

        Purchaser purchaser = new Purchaser();

        System.out.println("Please enter your name and surname!");

        purchaser = (Purchaser) getNameAndSurnameUser(purchaser);

//        Method to get all trips




    }

    private void cancelBooking() {

        boolean isContinue = true;
        int bookingID;
        int temp;

 OUTER :    while (isContinue) {

            System.out.println("Do you want to cancel booking please enter booking ID\n" +
                    "If you want to leave this mode please enter '0'!");

             boolean isNotCorrect = true;
             do {
                 if (scanner.hasNextInt()) {
                     temp = scanner.nextInt();
                     if (temp == 0) {
                         break OUTER;
                     } else {
                         bookingID = temp;
                         isNotCorrect = false;
                     }

                 } else System.out.println("Wrong input!\nPlease try again!");
             } while (isNotCorrect);


             // method to delete Booking


        }




    }

    /*Method to search available trips based in destination, date, required number of places
    * with method 'chooseTrip' and propose to make booking calling method 'makeBooking'*/

    private void searchAndBooking() {
        boolean isContinue = true;
        Destinations destination;
        LocalDate dateDeparture = null;
        int day = 0;
        int month = 0;
        int reserveNum;
        List <Trip> availableTrips = new ArrayList<>();
        Trip bookingTrip;



   OUTER:   while (isContinue) {

            System.out.println("Please enter destination!\n" +
                    "If you want to leave this mode please enter exit");
            String destinationName = scanner.next().trim().toUpperCase();
            String choice = destinationName.toLowerCase();

            switch (choice)  {

                case "exit" : {
                    break OUTER;
                }

            default: {
                    for (Trip trip : tripController.downLoadAllTrips()) {
                        if (trip.getDestination().equals(Destinations.valueOf(destinationName))) {
                            destination = Destinations.valueOf(destinationName);
                            break OUTER;
                        }
                    } System.out.println("Wrong input! Please try again!");
                }
            }

        }

    OUTER:   while (isContinue) {

        System.out.println("If you want to continue enter any symbol!\n" +
                "If you want to leave this mode please enter 'exit'!");

        String date = scanner.next().trim();
        String choice = date.toLowerCase();

        switch (choice) {

            case "exit": {
                break OUTER;
            }

            default: {

                boolean isNotCorrect = true;
                do {
                    System.out.println("Please enter day");
                    if (scanner.hasNextInt(32)) {
                        day = scanner.nextInt();
                        isNotCorrect = false;
                    } else System.out.println("Wrong input!\nPlease try again!");
                } while (isNotCorrect);

                isNotCorrect = true;

                do {
                    System.out.println("Please enter month");
                    if (scanner.hasNextInt(13)) {
                        month = scanner.nextInt();
                        isNotCorrect = false;
                    } else System.out.println("Wrong input!\nPlease try again!");
                } while (isNotCorrect);

             try {
                 dateDeparture = LocalDate.of (Year.now().getValue(),month,day);
             } catch (DateTimeException e) {
                 System.out.println("Wrong input!\nPlease try again!");
                 break;
             }

             if (dateDeparture.isBefore(LocalDate.now().plusMonths(TIME_BOOKING_IN_ADVANCE))
                     && dateDeparture.isAfter(LocalDate.now().minusDays(1))) {
                 break OUTER;

             } else System.out.println("Your date is out of time for reservation ");

             }


        }

    }

      while (isContinue) {
          System.out.println("Please enter the number places you need for booking");
          if (scanner.hasNextInt()) {
              reserveNum = scanner.nextInt();
                      if (reserveNum <= MAX_RESERVE_PLACES_PER_TRIP) {
                          isContinue = false;
                      } else System.out.println("Wrong input!\n" +
                              "Please enter number no more than " + MAX_RESERVE_PLACES_PER_TRIP);
          }
      }

//       availableTrips = bookingController.getAvailableTrips (destination, dateDeparture,reserveNum);
         bookingTrip = chooseTrip(availableTrips);

         if (bookingTrip != null) {
             makeBooking(bookingTrip, dateDeparture);
         } else System.out.println("Thank you being with AviaDreamer!");


    }
         /*Method get list of available trips and return chosen by user trip
          according to the sequential number for continue to book places on this trip*/
         private Trip chooseTrip(List <Trip> tripList) {
              Map <Integer, Trip> availableTripsMap = new HashMap<>();
              int count = 1;
              int choice = 0;
             for (Trip trip:
                  tripList) {
                 availableTripsMap.put (count++, trip);
             }

             for (Map.Entry <Integer, Trip> trip:
                  availableTripsMap.entrySet()) {
                  System.out.printf ("%d - %s", trip.getKey(), trip.getValue().prettyFormat());
             }

             System.out.println("Please chose number of trip\n" +
                     "If you want to exit enter '0");

             boolean isNotCorrect = true;
             do {
                 System.out.println("Please chose number of trip\n" +
                         "If you want to exit enter '0");
                 if (scanner.hasNextInt(tripList.size() + 1)) {
                     choice = scanner.nextInt();
                     if (choice == 0) {
                         break;
                     } else {
                         System.out.println("Make a reservation");
                         isNotCorrect = false;
                         return availableTripsMap.get(choice);
                        }
                 } else System.out.println("Wrong input!\nPlease try again!");
             } while (isNotCorrect);
             return null;
         }

         /*Method allows to book places entering Purchaser's name and surname,
          and Passengers' name and surname*/
         private void makeBooking (Trip bookingTrip, LocalDate dateDeparture) {
             Purchaser purchaser = new Purchaser();
             List <Passenger> passengerList = new ArrayList<>();
             Passenger passengerName = new Passenger();
             boolean isContinue = true;
             int reserveNum = 0;


         OUTER:   while (isContinue) {

             System.out.println("If you want to continue booking enter any symbol!\n" +
                     "If you want to leave this mode please enter 'exit'!");

             String choice = scanner.next().trim().toLowerCase();

             switch (choice) {

                 case "exit": {
                     break OUTER;
                 }

                 default: {

                     System.out.println("Please enter your name and surname");

                     purchaser = (Purchaser) getNameAndSurnameUser(purchaser);

                     isContinue = true;
                     while (isContinue) {
                         System.out.println("Please enter the number places you need for booking");
                         if (scanner.hasNextInt()) {
                             reserveNum = scanner.nextInt();
                             if (reserveNum <= MAX_RESERVE_PLACES_PER_TRIP) {                      // if there is possibility to get available places by Trip and Date
                                 isContinue = false;
                             } else System.out.println("Wrong input!\n" +
                                     "Please enter number no more than " + MAX_RESERVE_PLACES_PER_TRIP);
                         }
                     }

                     System.out.println("Please enter passengers' name and surname, " +
                             "including your credentials if you are passenger\n ");

                     while (reserveNum > 0) {
                         passengerList.add((Passenger) getNameAndSurnameUser(passengerName));
                     }

                     System.out.println("Do you want to finalize your booking?\n" +
                             "Enter yes or no");

                     choice = scanner.next();

                     if (choice == "yes") {
                         System.out.println("Create booking");   // transfer data to Booking.Controller
                         System.out.println("Your booking is successful\n" +
                                 "Thank you being with AviaDreamer!");
                     } else {
                         System.out.println("You cancelled process of booking! \n" +
                                 "Thank you being with AviaDreamer!");
                     }

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
        boolean isContinue = true;

 OUTER:     while (isContinue) {
            System.out.println("Please enter trip ID you have chosen!\n" +
                    "If you want to leave this mode please enter exit");
            String tripId = scanner.next().trim();
            String choice = tripId.toLowerCase();

            switch (choice)  {

                case "exit" : {
                    break OUTER;
                }

                default: {
                   for (Trip trip : tripController.downLoadAllTrips()) {
                        if (trip.getTripId().equals(tripId)) {
                            System.out.println("Get trip by id");
                            break OUTER;
                        }
                    } System.out.println("Wrong input! Please try again!");
                }
              }

      }

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

