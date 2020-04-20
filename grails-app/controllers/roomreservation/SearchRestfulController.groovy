package roomreservation

import grails.rest.RestfulController
import org.apache.commons.lang.StringUtils

/**
 * Custom RestfulController which allows searching.
 */
abstract class SearchRestfulController<T> extends RestfulController<T> {

    SearchRestfulController(Class<T> domainClass) {
        this(domainClass, false)
    }

    SearchRestfulController(Class<T> domainClass, boolean readOnly) {
        super(domainClass, readOnly)
    }

    def search(String q) {
        if (q) {
            // resource.findAllByNameIlike("%${q}%")
            // Explanation:
            //   - 'resource.getAll()' can be shortened as 'resource.all'
            //   - 'findAll' is a Groovy list method which accepts a Closure (https://www.tutorialspoint.com/groovy/groovy_findall.htm)
            def list = resource.all.findAll {
                StringUtils.containsIgnoreCase it.name, q
            }
            respond list
        } else {
            respond([])
        }
    }
}
