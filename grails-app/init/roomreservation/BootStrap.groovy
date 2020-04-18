package roomreservation

import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        if (Environment.current == Environment.PRODUCTION) {
            return
        }

        Room windowRoom = save(new Room(name: "1.333", max: 32))
        Room cornerRoom = save(new Room(name: "1.331", max: 42))
        Room cellarRoom = save(new Room(name: "1.332", max: 5))

        Person dierk = save(new Person(firstName: "Dierk", lastName: "König", email: "dierk.koenig@fhnw.ch"))
        Person christian = save(new Person(firstName: "Christian", lastName: "Ribeaud", email: "christian.ribeaud@fhnw.ch"))

        Date today = new Date().clearTime();
        save(new Booking(booker: dierk, room: windowRoom, date: today - 1, slot: Booking.AM))
        save(new Booking(booker: dierk, room: windowRoom, date: today, slot: Booking.AM))
        save(new Booking(booker: dierk, room: windowRoom, date: today + 1, slot: Booking.AM))
        save(new Booking(booker: dierk, room: cornerRoom, date: today, slot: Booking.AM))

        save(new Booking(booker: christian, room: windowRoom, date: today, slot: Booking.AM))
        save(new Booking(booker: christian, room: cornerRoom, date: today, slot: Booking.AM))

        // In production or test, this might already be in the DB
        Role adminRole = save(Role.findOrCreateWhere(authority: Role.ADMIN))
        Role userRole = save(Role.findOrCreateWhere(authority: Role.USER))

        User user = save(new User(username: 'user', password: 'user'))
        User admin = save(new User(username: 'admin', password: 'admin'))

        UserRole.create(user, userRole, true)
        UserRole.create(admin, adminRole, true)

        // Plausibility check
        assert Role.count() == 2
        assert User.count() == 2
        assert UserRole.count() == 2
    }

    static save(domainObject) {
        domainObject.save(failOnError: true)
    }

    def destroy = {
    }
}
