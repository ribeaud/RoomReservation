package roomreservation

import geb.spock.GebSpec
import grails.testing.mixin.integration.Integration
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.HttpClient
import groovy.json.JsonSlurper

@Integration
class PeopleRestSpec extends GebSpec {

    void "Test fetching people created in 'BootStrap.groovy' as JSON"() {
        setup:
        def client = HttpClient.create(new URL("http://localhost:${serverPort}/")).toBlocking()

        when: "Accessing '/people.json'"
        HttpRequest request = HttpRequest.GET("/people.json")
        HttpResponse<String> resp = client.exchange(request, String)
        def json = new JsonSlurper().parseText resp.body()

        then:
        resp.status.code == 200
        json.size() == 2
        json.find { it.firstName == 'Dierk' && it.lastName == 'König' }
        json.find { it.firstName == 'Christian' && it.lastName == 'Ribeaud' }
    }
}