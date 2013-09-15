package llc.web.scheduler.importing



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GoogleCalendarDomainEventController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond GoogleCalendarDomainEvent.list(params), model:[googleCalendarDomainEventInstanceCount: GoogleCalendarDomainEvent.count()]
    }

    def show(GoogleCalendarDomainEvent googleCalendarDomainEventInstance) {
        respond googleCalendarDomainEventInstance
    }

    def create() {
        respond new GoogleCalendarDomainEvent(params)
    }

    @Transactional
    def save(GoogleCalendarDomainEvent googleCalendarDomainEventInstance) {
        if (googleCalendarDomainEventInstance == null) {
            notFound()
            return
        }

        if (googleCalendarDomainEventInstance.hasErrors()) {
            respond googleCalendarDomainEventInstance.errors, view:'create'
            return
        }

        googleCalendarDomainEventInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'googleCalendarDomainEventInstance.label', default: 'GoogleCalendarDomainEvent'), googleCalendarDomainEventInstance.id])
                redirect googleCalendarDomainEventInstance
            }
            '*' { respond googleCalendarDomainEventInstance, [status: CREATED] }
        }
    }

    def edit(GoogleCalendarDomainEvent googleCalendarDomainEventInstance) {
        respond googleCalendarDomainEventInstance
    }

    @Transactional
    def update(GoogleCalendarDomainEvent googleCalendarDomainEventInstance) {
        if (googleCalendarDomainEventInstance == null) {
            notFound()
            return
        }

        if (googleCalendarDomainEventInstance.hasErrors()) {
            respond googleCalendarDomainEventInstance.errors, view:'edit'
            return
        }

        googleCalendarDomainEventInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'GoogleCalendarDomainEvent.label', default: 'GoogleCalendarDomainEvent'), googleCalendarDomainEventInstance.id])
                redirect googleCalendarDomainEventInstance
            }
            '*'{ respond googleCalendarDomainEventInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(GoogleCalendarDomainEvent googleCalendarDomainEventInstance) {

        if (googleCalendarDomainEventInstance == null) {
            notFound()
            return
        }

        googleCalendarDomainEventInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'GoogleCalendarDomainEvent.label', default: 'GoogleCalendarDomainEvent'), googleCalendarDomainEventInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'googleCalendarDomainEventInstance.label', default: 'GoogleCalendarDomainEvent'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
