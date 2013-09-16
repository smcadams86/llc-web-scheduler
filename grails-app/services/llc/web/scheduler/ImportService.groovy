package llc.web.scheduler

import java.util.Calendar;

import grails.transaction.Transactional
import llc.web.scheduler.importing.GoogleCalendarDomainEvent
import llc.web.scheduler.importing.ImportAttempt
import llc.web.scheduler.importing.FailedCalendarEvent

import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory

@Transactional
class ImportService {

    def excelImportService
    //
    //    static Map CONFIG_RLLC_SCHEDULE_COLUMN_MAP = [
    //        sheet:'Schedule',
    //        startRow: 3,
    //        columnMap:  [
    //			'B':'date',
    //			'C':'time',
    //			'D':'minister',
    //			'E':'event',
    //			'F':'comments',
    //        ]
    //    ]

    def executeImport(ImportAttempt importAttempt) {
        def columnMap = [:]
        importAttempt.excelMapping.each { excelMap ->
            columnMap[excelMap.columnName] = excelMap.googleField
        }
        
        def excelColumnMap = [
            sheet : importAttempt.sheetName,
            startRow : importAttempt.startRow,
            columnMap : columnMap
        ]

        Workbook wb = WorkbookFactory.create(new File(importAttempt.ufile.path));
        def eventsParsedFromExcel = excelImportService.columns(wb, excelColumnMap)
        println "eventsParsedFromExcel : ${eventsParsedFromExcel}"
        eventsParsedFromExcel.each { Map eventParams ->
            println "eventParams : ${eventParams}"
//            Calendar dateCalendar = Calendar.getInstance();
//            Calendar timeCalendar = Calendar.getInstance();
//            dateCalendar.setTime(eventParams.date?.toDate());
//            timeCalendar.setTime(eventParams.time?.toDate());
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(
//                dateCalendar.get(Calendar.YEAR),
//                dateCalendar.get(Calendar.MONTH),
//                dateCalendar.get(Calendar.DAY_OF_MONTH),
//                timeCalendar.get(Calendar.HOUR_OF_DAY),
//                timeCalendar.get(Calendar.MINUTE),
//                timeCalendar.get(Calendar.SECOND));
//				
            def googleCalendarEvent = new GoogleCalendarDomainEvent(eventParams)
//                title : "${eventParams.event} - ${eventParams.minister ? eventParams.minister : ''}",
//                startTime : calendar.time,
//                location : eventParams.location,
//                description : eventParams.minister + "\n" + eventParams.event + "\n" + eventParams.comments
//            )

            if (googleCalendarEvent.hasErrors()) {
                println "hasErrors..."
                importAttempt.addToFailedEvents(new FailedCalendarEvent(data : eventParams))
            }
            else {
                importAttempt.addToEvents(googleCalendarEvent)
            }
        }
		
        importAttempt.save(flush:true, failOnError:true)

		
    }
}
