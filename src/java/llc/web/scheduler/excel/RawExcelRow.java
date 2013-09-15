/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package llc.web.scheduler.excel;

import java.util.Date;

/**
 *
 * @author steve
 */
public class RawExcelRow {

    private String date;
    private String time;
    private String minister;
    private String event;
    private String comments;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
