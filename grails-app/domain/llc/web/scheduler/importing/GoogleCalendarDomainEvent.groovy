package llc.web.scheduler.importing

import java.util.Date;
import java.util.List;

class GoogleCalendarDomainEvent {

	static constraints = {
		description nullable:true
		location nullable:true
        endTime nullable:true
        title nullable:true
	}

	static belongsTo = [importAttempt : ImportAttempt]
	
	static hasMany = [
		guests : String
	]

	String title;
	Date startTime;
    Date endTime;
	String location;
	String description;
}
