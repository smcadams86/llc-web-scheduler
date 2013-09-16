package llc.web.scheduler.importing

import com.lucastex.grails.fileuploader.UFile

class UploadController {
	
    def importService

    def success() {
        println "success : ${params}"
		
        def ufile = UFile.get(params.long("ufileId"))
        if (ufile) {
            def importAttempt = new ImportAttempt(ufile : ufile).save(flush:true, failOnError:true)
//            			importService.executeImport(importAttempt)
            flash.message = "File uploaded!"
            redirect(controller : "importAttempt", action:"configure", id : importAttempt.id)
        }
        else {
            flash.message = "File not uploaded!"
            redirect "/"
        }
    }
	
    def error() {
        println "error : ${params}"
    }
}
