package llc.web.scheduler.importing

class FailedCalendarEventField {

    static constraints = {
        columnName(nullable:false)
        value(nullable:true)
        error(nullable:true)
    }
    
    String columnName
    String value
    String error
    
    static mapping = { 
        error type : 'text'
    }
    
    static belongsTo = [failedCalendarEvent : FailedCalendarEvent]
}
