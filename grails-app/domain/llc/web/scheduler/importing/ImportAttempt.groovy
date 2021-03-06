package llc.web.scheduler.importing

import com.lucastex.grails.fileuploader.UFile

class ImportAttempt {

    static constraints = {
        ufile (nullable:false)
        sheetName(nullable: true)
        startRow(nullable: true)
    }
    
    Date dateCreated 
    Date lastUpdated
	
    UFile ufile
    String sheetName
    int startRow
	
    static hasMany = [
        events : GoogleCalendarDomainEvent,
        failedEvents : FailedCalendarEvent,
        excelMapping : ExcelMap
    ]
	
    static mapping = {
        autoTimestamp true
         
        events cascade: 'all-delete-orphan'
        failedEvents cascade: 'all-delete-orphan'
    }
    
}
