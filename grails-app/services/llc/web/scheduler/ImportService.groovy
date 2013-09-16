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

            def params = [:]
            eventParams.each { k, v ->
              def value = v
              if (v instanceof org.joda.time.LocalDate) {
                  value = v.toDate()?.toString()
              }
              params[k] = value
            }
            def googleCalendarEvent = new GoogleCalendarDomainEvent(params)
            if (googleCalendarEvent.validate()) {
                importAttempt.addToEvents(googleCalendarEvent)
            }
            else {
                importAttempt.addToFailedEvents(new FailedCalendarEvent(data : params))
            }
        }
		
        importAttempt.save(flush:true, failOnError:true)

		
    }
}
