/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package llc.web.scheduler.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author steve
 */
public class GoogleCalendarEvent {

    private String title;
    private Date startTime;
    private String location;
    private String description;
    private List<String> guests;
    
    public String toString() {
        return "[title :" + title + ", " + 
                "startTime :" + startTime + ", " + 
                "location :" + location + ", " + 
                "description :" + description + "]";
    }

    public GoogleCalendarEvent(RLLCScheduledEvent event) {

        this.title = event.getEvent();
        if (!"".equals(event.getMinister().trim())) {
            this.title += " - " + event.getMinister();
        }

        this.startTime = event.getDate();
        this.location = "";
        this.description = event.getMinister() + "\n"
                + event.getEvent() + "\n"
                + event.getComments();
        guests = new ArrayList<String>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getGuests() {
        return guests;
    }

    public void setGuests(List<String> guests) {
        this.guests = guests;
    }
}
