package llc.web.scheduler

import grails.transaction.Transactional
import com.google.api.client.*
import com.google.api.services.calendar.*

@Transactional
class GoogleCalendarService {

    def APPLICATION_NAME = "llc.web-scheduler-1"
    
    def list(token) {
//        
//        
//        GoogleCredential credential = new GoogleCredential().setAccessToken(token);
//        println "credential : $credential"
//        
//        
//        
//         Calendar client = Calendar.builder(
//             new NetHttpTransport(),
//             new JacksonFactory())
//         .setApplicationName("llc-web-scheduler/1.0")
//         .setHttpRequestInitializer(credential)
//         .build();
//        
//        
//        CalendarList feed = client.calendarList().list().execute();
//        println "feed : $feed"
        
        
    }
    
    
    
}
