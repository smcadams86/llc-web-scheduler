package llc.web.scheduler.importing



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ImportAttemptController {
    
    def importService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ImportAttempt.list(params), model:[importAttemptInstanceCount: ImportAttempt.count()]
    }

    def show(ImportAttempt importAttemptInstance) {
        respond importAttemptInstance
    }

    def create() {
        respond new ImportAttempt(params)
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

    def edit(ImportAttempt importAttemptInstance) {
        respond importAttemptInstance
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
                redirect action:"index", method:"GET"
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
}
