package org.stepProjectBooking.ticketsApplication.DAO.bookingDAO;

import org.stepProjectBooking.ticketsApplication.trips.Booking;

import java.util.List;
import java.util.UUID;

public interface BookingDao {

    /**Возвращает коллекцию со всеми бронями */
    List<Booking> getAllBookings();

    /**Возвращает бронь по id*/
    Booking getBookingById(UUID id);

    /**Сохраняет бронь*/
    void saveBooking (Booking booking);

    /**Удаляет бронь по id*/
    void deleteBookingById(UUID id);








}
