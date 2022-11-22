import org.junit.jupiter.api.Test;
import org.stepProjectBooking.ticketsApplication.DAO.bookingDAO.BookingController;
import org.stepProjectBooking.ticketsApplication.trips.Departures;
import org.stepProjectBooking.ticketsApplication.trips.Destinations;
import org.stepProjectBooking.ticketsApplication.trips.Trip;
import org.stepProjectBooking.ticketsApplication.user.Passenger;
import org.stepProjectBooking.ticketsApplication.user.Purchaser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class TestTicketsApplication {

    BookingController bookingController = new BookingController();

    @Test
    public void getBookingById() {
        assertEquals("Booking{\n" +
                "idBooking=123456, \n" +
                "purchaser=Purchaser{userId=0name='Bill', surname='Smith', email='null', bookings=null}, \n" +
                "trip=Trip{tripId='KP475', timeTrip=18:50, departure=KYIV, destination=PARIS, capacity=25}, \n" +
                "reserveNum=124523, \n" +
                "passengerList=[Passenger{name='Bill', surname='Soyer'tripId='Trip{tripId='KP475', timeTrip=11:05, " +
                "departure=KYIV, destination=PARIS, capacity=10}', bookId=124523}\n" +
                ", Passenger{name='Mike', surname='Murray'tripId='Trip{tripId='KP475', timeTrip=11:05, " +
                "departure=KYIV, destination=PARIS, capacity=10}', bookId=124523}\n" +
                ", Passenger{name='Liza', surname='Trust'tripId='Trip{tripId='KP475', timeTrip=11:05, " +
                "departure=KYIV, destination=PARIS, capacity=10}', bookId=124523}\n" +
                ", Passenger{name='Indy', surname='Chart'tripId='Trip{tripId='KP475', timeTrip=11:05, " +
                "departure=KYIV, destination=PARIS, capacity=10}', bookId=124523}\n" +
                "]}", bookingController.getBookingById(123456).toString());
    }

    @Test
    public void deleteBookingById() {
        assertTrue(bookingController.deleteBookingById(123456));
    }



    @Test
    public void getTripInfoById() {
        assertEquals("TripBooking{trip=Trip{tripId='KP931', timeTrip=00:40, " +
                        "departure=KYIV, destination=PARIS, capacity=25}, bookingList=[], freePlace=25}",
                bookingController.getTripInfoById("KP931").toString());
    }

    @Test
    public void getAvailableTrips() {
        assertEquals("[Trip{tripId='KP475', timeTrip=18:50, departure=KYIV, destination=PARIS, capacity=25}, " +
                        "Trip{tripId='KC021', timeTrip=10:40, departure=KYIV, destination=PARIS, capacity=35}, " +
                        "Trip{tripId='KP931', timeTrip=00:40, departure=KYIV, destination=PARIS, capacity=25}]",
                bookingController.getAvailableTrips(Destinations.PARIS,
                        LocalDate.of(2022, 12, 25), 10).toString());
        assertEquals("[Trip{tripId='KC021', timeTrip=10:40, departure=KYIV, destination=PARIS, capacity=35}]",
                bookingController.getAvailableTrips(Destinations.PARIS,
                        LocalDate.of(2022, 12, 25), 26).toString());
        assertEquals("[]",
                bookingController.getAvailableTrips(Destinations.PARIS,
                        LocalDate.of(2022, 8, 25), 10).toString());
    }

    public static List<Passenger> createPassengerList() {
        List<Passenger> passengerList = new ArrayList<>();
        Trip KP475 = new Trip("KP475", LocalTime.of(18, 50), Departures.KYIV, Destinations.PARIS, 20);
        passengerList.add(new Passenger("Bill", "Soyer", KP475, 124523));
        passengerList.add(new Passenger("Mike", "Murray", KP475, 124523));
        passengerList.add(new Passenger("Liza", "Trust", KP475, 124523));
        passengerList.add(new Passenger("Indy", "Chart", KP475, 124523));
        return passengerList;
    }

    @Test
    public void createNewBooking() {
        Purchaser billSmith = new Purchaser("Bill", "Smith");
        Trip KP475 = new Trip("KP475", LocalTime.of(18, 50), Departures.KYIV, Destinations.PARIS, 25);

        assertTrue(bookingController.createNewBooking(billSmith, KP475, createPassengerList(),
                LocalDate.of(2022, 12, 25)) > 0);
    }
}



