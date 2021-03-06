package llc.web.scheduler.importing



import static org.springframework.http.HttpStatus.*
import grails.converters.XML
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ImportAttemptController {
    
    def importService
    def oauthService
    def googleCalendarService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        redirect action : 'list'
    }
    
    def list() {
        respond ImportAttempt.list()
    }

    def show(ImportAttempt importAttemptInstance) {
        respond importAttemptInstance
    }

    @Transactional
    def save(ImportAttempt importAttemptInstance) {
        if (importAttemptInstance == null) {
            notFound()
            return
        }

        if (importAttemptInstance.hasErrors()) {
            respond importAttemptInstance.errors, view:'create'
            return
        }

        importAttemptInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'importAttemptInstance.label', default: 'ImportAttempt'), importAttemptInstance.id])
                redirect importAttemptInstance
            }
            '*' { respond importAttemptInstance, [status: CREATED] }
        }
    }

    @Transactional
    def update(ImportAttempt importAttemptInstance) {
        if (importAttemptInstance == null) {
            notFound()
            return
        }

        if (importAttemptInstance.hasErrors()) {
            respond importAttemptInstance.errors, view:'edit'
            return
        }

        importAttemptInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ImportAttempt.label', default: 'ImportAttempt'), importAttemptInstance.id])
                redirect importAttemptInstance
            }
            '*'{ respond importAttemptInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ImportAttempt importAttemptInstance) {

        if (importAttemptInstance == null) {
            notFound()
            return
        }

        importAttemptInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ImportAttempt.label', default: 'ImportAttempt'), importAttemptInstance.id])
                redirect uri : "/"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'importAttemptInstance.label', default: 'ImportAttempt'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
    
    def configure(ImportAttempt importAttemptInstance) {
        respond importAttemptInstance
    }
    
    def executeImport(ImportAttempt importAttemptInstance) {
        
        if (importAttemptInstance == null) {
            notFound()
            return
        }
        
        importService.executeImport(importAttemptInstance)
        flash.message="import executed"
        
        redirect action:'show', id:importAttemptInstance.id
    }
    
    def getGoogleAccessToken() {
        String sessionKey = oauthService.findSessionKeyForAccessToken('google')
        return session[sessionKey]
    }

    
    def export(ImportAttempt importAttemptInstance) {
        
        if (importAttemptInstance == null) {
            notFound()
            return
        }
        
        [importAttemptInstance : importAttemptInstance, calendars : googleCalendarService.list(getGoogleAccessToken()), createCalendarSuccess : params.boolean('createCalendarSuccess'), createCalendarMessage : params['createCalendarMessage']]
    }
    
    def executeExport(ImportAttempt importAttemptInstance) {
        
        if (importAttemptInstance == null) {
            notFound()
            return
        }
        
        def result = googleCalendarService.export(getGoogleAccessToken(), params.boolean('sendNotifications'), params.string('calendarId'), importAttemptInstance)
        
    }
    
    def newCalendar(ImportAttempt importAttemptInstance) {
        
        if (importAttemptInstance == null) {
            notFound()
            return
        }
        
        def result = googleCalendarService.create(getGoogleAccessToken(), params['summary'], params['timezone'])
        redirect action : 'export', id : importAttemptInstance.id,  params:[createCalendarSuccess : result.success, createCalendarMessage : result.message]
    }
        
    
    def changeStartRow(ImportAttempt importAttemptInstance) {
        if (importAttemptInstance == null) {
            notFound()
            return
        }
        importAttemptInstance.properties['startRow'] = params
        render "Start Row updated"
    }
}
