import llc.web.scheduler.importing.ExcelMap
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
                path : "2013_calendar.xlsx",
                name : "2013_calendar",
                extension : "xlsx",
                dateUploaded : new Date(),
                downloads : 0
            ).save(flush:true, failOnError:true)

         def excelMappingList = []
         excelMappingList << new ExcelMap(['columnName' : 'D', 'googleField' : 'startTime'])
         excelMappingList << new ExcelMap(['columnName' : 'I', 'googleField' : 'title'])
         excelMappingList << new ExcelMap(['columnName' : 'G', 'googleField' : 'description'])
        
        def params = [
            ufile : ufile,
            'sheetName' : 'Schedule',
            'startRow' : 3,
            'excelMapping' : excelMappingList
        ]
        
        new ImportAttempt(params).save(flush:true, failOnError:true)
        
    }
    def destroy = {
    }
}
