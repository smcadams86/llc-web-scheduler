package llc.web.scheduler

import grails.transaction.Transactional
import com.google.api.client.*
import com.google.api.services.calendar.*
import grails.converters.*

@Transactional
class GoogleCalendarService {

    def oauthService
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
}
