/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package llc.web.scheduler.excel;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import llc.web.scheduler.model.GoogleCalendarEvent;
import llc.web.scheduler.model.RLLCScheduledEvent;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author steve
 */
public class ExcelImporter {

    private List<RawExcelRow> rawRows;
    private List<RLLCScheduledEvent> rllcScheduledEvents;
    private List<GoogleCalendarEvent> googleCalendarEvents;
    private String resourceFile;
    private static final DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    public ExcelImporter(String resourceFile) {
        this.rawRows = new ArrayList<RawExcelRow>();
        this.rllcScheduledEvents = new ArrayList<RLLCScheduledEvent>();
        this.googleCalendarEvents = new ArrayList<GoogleCalendarEvent>();
        this.resourceFile = resourceFile;
    }

    public void run() {
        parseFile(resourceFile);
        for (RawExcelRow row : rawRows) {
            rllcScheduledEvents.add(new RLLCScheduledEvent(row));
        }
        for (RLLCScheduledEvent event : rllcScheduledEvents) {
            googleCalendarEvents.add(new GoogleCalendarEvent(event));
        }

        for (GoogleCalendarEvent gEvent : googleCalendarEvents) {
            System.out.println(gEvent);
        }

    }
    
    public List<GoogleCalendarEvent> getGoogleCalendarEvents() {
        return googleCalendarEvents;
    }

    private void parseFile(String resourceFile) {
        try {
            // Use a file
            InputStream is = ExcelImporter.class.getResourceAsStream(resourceFile);
            Workbook wb = WorkbookFactory.create(is);
            Sheet sheet1 = wb.getSheetAt(0);
            for (Row row : sheet1) {
                if (row.getRowNum() > 2 && !"".equals(parseCellValueAsString(row.getCell(1)))) {
                    RawExcelRow rawExcelRow = new RawExcelRow();
                    rawExcelRow.setDate(parseCellValueAsString(row.getCell(1)));
                    rawExcelRow.setTime(parseCellValueAsString(row.getCell(2)));
                    rawExcelRow.setMinister(parseCellValueAsString(row.getCell(3)));
                    rawExcelRow.setEvent(parseCellValueAsString(row.getCell(4)));
                    rawExcelRow.setComments(parseCellValueAsString(row.getCell(5)));
                    rawRows.add(rawExcelRow);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ExcelImporter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(ExcelImporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String parseCellValueAsString(Cell cell) {
        String value = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    value = cell.getRichStringCellValue().getString();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        value = df.format(cell.getDateCellValue());
                    } else {
                        value = cell.getNumericCellValue() + "";
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    value = cell.getBooleanCellValue() + "";
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    value = cell.getCellFormula() + "";
                    break;
            }
        }
        return value;
    }
}
