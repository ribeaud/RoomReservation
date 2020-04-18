package roomreservation

import grails.plugin.springsecurity.annotation.Secured

@Secured(Role.USER)
class BookingController {

    static scaffold = Booking

    // http://localhost:8080/booking/forDierk
    def forDierk() {
        Person dierk = Person.findByEmail("dierk.koenig@fhnw.ch")
        List<Booking> result = Booking.findAllByBooker(dierk);
        // render text: result.toString()
        render view: "OpenBookingsFor", model: [bookings: result]
    }

    // http://localhost:8080/booking/all.json or http://localhost:8080/booking/all.xml
    def all() {
        respond Booking.list()
    }

    // http://localhost:8080/booking/freeTodayMorning
    def freeTodayMorning() {

        // allRooms = find all rooms
        List<Room> allRooms = Room.findAll()

        // allBookings for this morning
        Date today = new Date().clearTime()
        String morning = Booking.AM
        List<Booking> allBookingsThisMorning = Booking.findAllByDateAndSlot(today, morning);

        // allRoomsBookedThisMorning
        List<Room> allRoomsBookedThisMorning = allBookingsThisMorning*.room

        List<Room> result = allRooms - allRoomsBookedThisMorning

        // respond result
        render view: "AvailableRoomsFor", model: [rooms: result]
    }

}
