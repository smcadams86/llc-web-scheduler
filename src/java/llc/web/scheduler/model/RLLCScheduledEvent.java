/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package llc.web.scheduler.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import llc.web.scheduler.excel.RawExcelRow;

/**
 *
 * @author steve
 */
public class RLLCScheduledEvent {

    private static final DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private Date date;
    private String minister;
    private String event;
    private String comments;

    public RLLCScheduledEvent(RawExcelRow row) {
        try {
            Calendar dateCalendar = Calendar.getInstance();
            Calendar timeCalendar = Calendar.getInstance();
            dateCalendar.setTime(df.parse(row.getDate()));
            timeCalendar.setTime(df.parse(row.getTime()));

            Calendar calendar = Calendar.getInstance();
            calendar.set(
                    dateCalendar.get(Calendar.YEAR),
                    dateCalendar.get(Calendar.MONTH),
                    dateCalendar.get(Calendar.DAY_OF_MONTH),
                    timeCalendar.get(Calendar.HOUR_OF_DAY),
                    timeCalendar.get(Calendar.MINUTE),
                    timeCalendar.get(Calendar.SECOND));

            date = calendar.getTime();
        } catch (ParseException ex) {
            Logger.getLogger(RLLCScheduledEvent.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.minister = row.getMinister();
        this.event = row.getEvent();
        this.comments = row.getComments();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMinister() {
        return minister;
    }

    public void setMinister(String minister) {
        this.minister = minister;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
