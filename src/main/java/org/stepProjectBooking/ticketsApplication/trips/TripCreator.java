package org.stepProjectBooking.ticketsApplication.trips;

import java.io.Serializable;
import java.time.LocalTime;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class TripCreator implements Serializable {


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


    private String createTripId (int idNumeric){

        return String.format("%3s%03d",Trip.SHORT_NAME_COMPANY,idNumeric);
    }
    private LocalTime createTimeDeparture () {

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

           return LocalTime.of(hour,minute);

    }
    public Trip createTrip () {
           Destinations Destination;
           Departures Departure;
           String tripId;

           LocalTime timeDeparture;

           int capacity = 100;
           int idNumeric;
           idNumeric = listIdTrip.get(idTripCounter);
           listIdTrip.remove(idTripCounter++);
           timeDeparture = createTimeDeparture();
           Destination = createDestination();
           Departure = createDeparture();

           tripId  = createTripId(idNumeric);

           return new Trip(tripId, timeDeparture, Departure, Destination, capacity);
    }

}
