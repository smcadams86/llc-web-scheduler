package llc.web.scheduler.importing

class FailedCalendarEvent {

    static constraints = {
        data : nullable : false
    }
	
    static belongsTo = [importAttempt : ImportAttempt]
    Map data
}
