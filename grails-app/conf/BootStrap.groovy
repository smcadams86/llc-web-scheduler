import llc.web.scheduler.importing.ImportAttempt
import com.lucastex.grails.fileuploader.UFile
class BootStrap {

    def init = { servletContext ->
        
//        ExcelImporter calendarImporter = new ExcelImporter("2013_calendar.xlsx");
//        calendarImporter.run();
//        
//        List<GoogleCalendarEvent> events = calendarImporter.getGoogleCalendarEvents();

        def file = new File("2013_calendar.xlsx")
        def ufile = new UFile(
                size : file.size(),
                path : "/",
                name : "2013_calendar",
                extension : "xlsx",
                dateUploaded : new Date(),
                downloads : 0
            ).save(flush:true, failOnError:true)

        new ImportAttempt(ufile : ufile).save(flush:true, failOnError:true)
        
    }
    def destroy = {
    }
}
