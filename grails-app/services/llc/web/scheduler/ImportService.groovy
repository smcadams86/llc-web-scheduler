package llc.web.scheduler

import grails.transaction.Transactional
import llc.web.scheduler.importing.FailedCalendarEvent
import llc.web.scheduler.importing.FailedCalendarEventField
import llc.web.scheduler.importing.GoogleCalendarDomainEvent
import llc.web.scheduler.importing.ImportAttempt

import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory

class ImportService {

    def excelImportService

    @Transactional(readOnly = false)
    def executeImport(ImportAttempt importAttempt) {
        importAttempt.events?.clear()
        importAttempt.failedEvents?.clear()
        
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
        eventsParsedFromExcel.each { Map eventParams ->
            def params = [:]
            eventParams.each { k, v ->
              def value = v
              if (v instanceof org.joda.time.LocalDate) {
                  value = v.toDate()
              }
              params[k] = value
            }
            def googleCalendarEvent = new GoogleCalendarDomainEvent(params)
            googleCalendarEvent.importAttempt = importAttempt
            if (googleCalendarEvent.validate()) {
                importAttempt.addToEvents(googleCalendarEvent)
            }
            else {
                def failedCalendarEvent = new FailedCalendarEvent()
                params.each { k, v ->
                    def error = googleCalendarEvent.errors.getFieldError(k)
                    failedCalendarEvent.addToFailedFields(
                        new FailedCalendarEventField(
                            columnName : k,
                            value : v,
                            error : error ? error.toString() : ''
                        )
                    )
                }
                importAttempt.addToFailedEvents(failedCalendarEvent)
            }
        }
		
        importAttempt.save(flush:true, failOnError:true)
    }
}
