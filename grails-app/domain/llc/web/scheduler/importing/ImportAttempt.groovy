package llc.web.scheduler.importing

import com.lucastex.grails.fileuploader.UFile

class ImportAttempt {

    static constraints = {
        ufile (nullable:false)
        sheetName(nullable: true)
        startRow(nullable: true)
    }
	
    UFile ufile
    String sheetName
    int startRow
	
    static hasMany = [
        events : GoogleCalendarDomainEvent,
        failedEvents : FailedCalendarEvent,
        excelMapping : ExcelMap
    ]
	
    static mapping = { 
        events cascade: 'all-delete-orphan'
        failedEvents cascade: 'all-delete-orphan'
    }
	
}
