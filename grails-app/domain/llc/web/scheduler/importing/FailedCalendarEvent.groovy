package llc.web.scheduler.importing

class FailedCalendarEvent {

    static constraints = {
        failedFields(nullable:false)
    }
    
    static hasMany = [
        failedFields : FailedCalendarEventField
    ]
    
    static mapping = { 
        failedFields cascade: 'all-delete-orphan'
    }
	
    static belongsTo = [importAttempt : ImportAttempt]
}
