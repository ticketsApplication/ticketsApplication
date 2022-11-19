import org.junit.jupiter.api.Test;
import org.stepProjectBooking.ticketsApplication.DAO.bookingDAO.BookingController;

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
                "]}",bookingController.getBookingById(123456).toString());
    }
    @Test
    public void deleteBookingById() {
        assertTrue(bookingController.deleteBookingById(123456));
    }
}
