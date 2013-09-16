import llc.web.scheduler.excel.ExcelImporter
import llc.web.scheduler.model.GoogleCalendarEvent
class BootStrap {

    def init = { servletContext ->
        
//        ExcelImporter calendarImporter = new ExcelImporter("2013_calendar.xlsx");
//        calendarImporter.run();
//        
//        List<GoogleCalendarEvent> events = calendarImporter.getGoogleCalendarEvents();
    }
    def destroy = {
    }
}
