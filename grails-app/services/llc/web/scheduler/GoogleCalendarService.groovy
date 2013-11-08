package llc.web.scheduler

import grails.converters.*
import grails.transaction.Transactional
import llc.web.scheduler.importing.ImportAttempt

import org.scribe.builder.*
import org.scribe.builder.api.*
import org.scribe.model.*
import org.scribe.oauth.*

@Transactional
class GoogleCalendarService {

    def oauthService
    def grailsApplication
    
    def list(token) {
        def calendarList = []
        if (token) {
            def calendars = oauthService.getGoogleResource(token, 'https://www.googleapis.com/calendar/v3/users/me/calendarList?key=${grailsApplication.config.oauth.providers.google.key}')
            def calendarsJSON = JSON.parse(calendars.body)
            
            for (int i = 0; i < calendarsJSON.items?.id?.size; i ++) {
                def gcm = new GoogleCalendarMeta();

                gcm.summary = calendarsJSON.items.summary[i]
                gcm.id = calendarsJSON.items.id[i]
                gcm.backgroundColor = calendarsJSON.items.backgroundColor[i]
                gcm.description = calendarsJSON.items.description[i]
                gcm.colorId = calendarsJSON.items.colorId[i]
                gcm.timeZone = calendarsJSON.items.timeZone[i]
                gcm.accessRole = calendarsJSON.items.accessRole[i]
                gcm.foregroundColor = calendarsJSON.items.foregroundColor[i]

                calendarList << gcm
                
            }
            return calendarList
        }
    }
    
    private Map fetchConfig() {

        Map conf = grailsApplication.config.oauth
        if (!conf) {
            throw new IllegalStateException('No oauth configuration found. Please configure the oauth scribe plugin')
        }

        return conf
    }
    
    def create(token, summary, timezone) {
        if (token) {
            Map conf = fetchConfig()
            def newCalendarUrl = "https://www.googleapis.com/calendar/v3/calendars"
            def payload = """
            {
                summary: "${summary}",
                timeZone: "${timezone}"
            }
            """
            
            def jsonResponse = JSON.parse(oauthService.postGoogleResourceWithPayload(token, newCalendarUrl, payload, 'application/json').body)
            if (jsonResponse.error) {
                return [success : false, message : jsonResponse.error.message]
            }
            else {
                return [success : true, message : 'Calendar created!']
            }
        }
        else {
            return [success : false, message : 'Not authenticated with Google.']
        }
    }
    
    def export(token, boolean sendNotifications, String calendarId, ImportAttempt importAttemptInstance) {
        def postUrl = "https://www.googleapis.com/calendar/v3/calendars/${calendarId}/events?key=${grailsApplication.config.oauth.providers.google.key}&sendNotifications=${sendNotifications}"
        importAttemptInstance.events?.each { event ->
            // TODO : form up JSON calendar event object
            // TODO : post calendar event object
            // TODO : if success, delete GoogleCalendarEvent
            // TODO : if failure, flag error
        }
        println oauthService.postGoogleResource(token, postUrl).body
    }
}
