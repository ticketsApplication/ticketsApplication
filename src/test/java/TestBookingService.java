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

import static org.testng.AssertJUnit.*;

public class TestBookingService {

    BookingController bookingController = new BookingController();

    @Test
    public void getBookingById() {
        assertEquals(null, bookingController.getBookingById(123456));
    }

    @Test
    public void deleteBookingById() {
        assertFalse(bookingController.deleteBookingById(123456));
    }

    @Test
    public void getBookingByNameSurname() {
        assertEquals("[Booking{\n" +
                "idBooking=1584898298, \n" +
                "purchaser=Purchaser{userId=0name='Bill', surname='Smith', email='null', bookings=null}, \n" +
                "trip=Trip{tripId='ADM015', timeTrip=15:05, departure=KYIV, destination=PARIS, capacity=100}, \n" +
                "passengerList=[Passenger{name='Sarah', surname='Smith', tripId='ADM015', bookId=1584898298}\n" +
                "]}]", bookingController.getBookingByNameSurname(new Passenger("Bill", "Smith")).toString());
    }

    @Test
    public void getTripInfoById() {
        assertEquals("TripBooking{trip=Trip{tripId='ADM927', timeTrip=20:30, departure=KYIV, destination=BUCHAREST, capacity=100}, bookingList=[], freePlace=100, date=2022-11-27T20:30}",
                bookingController.getTripInfoById("ADM927").toString());
    }

    @Test
    public void getAvailableTrips() {
        assertEquals("[Trip{tripId='ADM187', timeTrip=21:10, departure=KYIV, destination=PARIS, capacity=100}]",
                bookingController.getAvailableTrips(Destinations.PARIS,
                        LocalDate.of(2022, 11, 27), 10).toString());
        assertEquals("[]",
                bookingController.getAvailableTrips(Destinations.PARIS,
                        LocalDate.of(2022, 11, 26), 101).toString());
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



