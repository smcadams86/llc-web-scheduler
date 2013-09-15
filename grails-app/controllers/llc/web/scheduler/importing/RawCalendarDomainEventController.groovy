package llc.web.scheduler.importing



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RawCalendarDomainEventController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond RawCalendarDomainEvent.list(params), model:[rawCalendarDomainEventInstanceCount: RawCalendarDomainEvent.count()]
    }

    def show(RawCalendarDomainEvent rawCalendarDomainEventInstance) {
        respond rawCalendarDomainEventInstance
    }

    def create() {
        respond new RawCalendarDomainEvent(params)
    }

    @Transactional
    def save(RawCalendarDomainEvent rawCalendarDomainEventInstance) {
        if (rawCalendarDomainEventInstance == null) {
            notFound()
            return
        }

        if (rawCalendarDomainEventInstance.hasErrors()) {
            respond rawCalendarDomainEventInstance.errors, view:'create'
            return
        }

        rawCalendarDomainEventInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'rawCalendarDomainEventInstance.label', default: 'RawCalendarDomainEvent'), rawCalendarDomainEventInstance.id])
                redirect rawCalendarDomainEventInstance
            }
            '*' { respond rawCalendarDomainEventInstance, [status: CREATED] }
        }
    }

    def edit(RawCalendarDomainEvent rawCalendarDomainEventInstance) {
        respond rawCalendarDomainEventInstance
    }

    @Transactional
    def update(RawCalendarDomainEvent rawCalendarDomainEventInstance) {
        if (rawCalendarDomainEventInstance == null) {
            notFound()
            return
        }

        if (rawCalendarDomainEventInstance.hasErrors()) {
            respond rawCalendarDomainEventInstance.errors, view:'edit'
            return
        }

        rawCalendarDomainEventInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'RawCalendarDomainEvent.label', default: 'RawCalendarDomainEvent'), rawCalendarDomainEventInstance.id])
                redirect rawCalendarDomainEventInstance
            }
            '*'{ respond rawCalendarDomainEventInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(RawCalendarDomainEvent rawCalendarDomainEventInstance) {

        if (rawCalendarDomainEventInstance == null) {
            notFound()
            return
        }

        rawCalendarDomainEventInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'RawCalendarDomainEvent.label', default: 'RawCalendarDomainEvent'), rawCalendarDomainEventInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'rawCalendarDomainEventInstance.label', default: 'RawCalendarDomainEvent'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
