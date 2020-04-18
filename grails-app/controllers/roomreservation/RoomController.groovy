package roomreservation

import grails.plugin.springsecurity.annotation.Secured

@Secured("permitAll")
class RoomController {

    static scaffold = Room

}
