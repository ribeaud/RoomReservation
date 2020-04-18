package roomreservation

import grails.plugin.springsecurity.annotation.Secured

@Secured(Role.ADMIN)
class UserController {

    static scaffold = User
}
