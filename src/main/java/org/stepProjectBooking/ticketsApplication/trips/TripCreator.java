package org.stepProjectBooking.ticketsApplication.trips;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class TripCreator {


    Random random = new Random();

    private int idTripCounter = 0; // follow for removing id from listIdTrip
    private List<Integer> listIdTrip = ThreadLocalRandom.current()// array of unique numbers for creating id for trips
            .ints(1, 999) // id range
            .boxed()
            .distinct()
            .limit(500) // 500 id creation
            .collect(Collectors.toList());


//    private void fillIdTrip () {
//        while (setIdTrip.size() != 500) {
//            int randInt = ThreadLocalRandom.current().nextInt(0, 999);
//            setIdTrip.add(randInt);
//        }
//    }
   private Destinations createDestination () {
       int index = random.nextInt(Destinations.values().length);
       return Destinations.getName(index);
   }

   private Departures createDeparture () {
       return Departures.KYIV;
   }

    private String createTripId (Departures Departure, Destinations Destination, int idNumeric){
        String idDestination = Destination.toString().charAt(0) + "";
        String idDeparture = Departure.toString().charAt(0) + "";
        return String.format("%s%s%03d",idDeparture,idDestination,idNumeric);
    }
    private String createTimeDeparture () {
           boolean isContinue = true;
           int hour;
           int minute = 0;
           while (isContinue) {
               minute = random.nextInt(60);
               if (minute % 5 == 0) {
                  isContinue = false;
               }
           }
           hour = random.nextInt(24);
           return String.format("%02d:%02d", hour,minute);
    }
    public Trip createTrip () {
           Destinations Destination;
           Departures Departure;
           String tripId;
           String timeDeparture;
           int capacity = 100;
           int idNumeric;
           idNumeric = listIdTrip.get(idTripCounter);
           listIdTrip.remove(idTripCounter++);
           timeDeparture = createTimeDeparture();
           Destination = createDestination();
           Departure = createDeparture();

           tripId  = createTripId(Departure,Destination,idNumeric);
           return new Trip(tripId, timeDeparture, Departure, Destination, capacity);
    }

}
