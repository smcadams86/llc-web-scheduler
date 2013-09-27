package llc.web.scheduler

import grails.converters.*

class CalendarController {
    
    def grailsApplication
    def oauthService

    def index() { }
    
    def getGoogleAccessToken() {
        String sessionKey = oauthService.findSessionKeyForAccessToken('google')
        return session[sessionKey]
    }
    
    def refreshToken() {
        def conf = fetchConfig()
        def refreshUrl = "https://accounts.google.com/o/oauth2/token"
        def refreshParams = [
            'refresh_token' : getGoogleAccessToken().token,
            'client_id' : conf.providers.google.key,
            'client_secret' : conf.providers.google.secret,
            'grant_type' : 'refresh_token'
        ]
        def jsonResponse = JSON.parse(oauthService.postGoogleResource(getGoogleAccessToken(), refreshUrl, refreshParams).body)
        def newToken = new org.scribe.model.Token(jsonResponse.access_token, conf.providers.google.secret)
        return newToken
    }
    
    
    
    
    private Map fetchConfig() {
        Map conf = grailsApplication.config.oauth
        if (!conf) {
            throw new IllegalStateException('No oauth configuration found. Please configure the oauth scribe plugin')
        }
        return conf
    }
    
    def createTest() {
        
        def token = getGoogleAccessToken()
        def getCalendarUrl = "https://www.googleapis.com/calendar/v3/calendars/5nbpkdua0hk3nbs0lphf5igsps@group.calendar.google.com"
        println oauthService.getGoogleResource(token, getCalendarUrl).body
        
        
        
        def newCalendarUrl = "https://www.googleapis.com/calendar/v3/calendars"
        def newCalendar = [
            "kind": "calendar#calendar",
            "summary": "TESTING CALENDAR...",
            "description": "DELETE THIS CALENDAR"
        ]
        def headers = [
            "Content-type" : "application/json",
            "Authorization" : "Bearer ${token.token}"
        ]
        def i = 0
        def jsonResponse = JSON.parse(oauthService.postGoogleResource(token, newCalendarUrl, newCalendar, headers).body)
        while (jsonResponse.error && i < 5) {
            token = refreshToken()
            headers = [
                "Content-type" : "application/json",
                "Authorization" : "Bearer ${token.token}"
            ]
            
            println "retry attempt [$i]..."
            jsonResponse = JSON.parse(oauthService.postGoogleResource(refreshToken(), newCalendarUrl, newCalendar, headers).body)
            println jsonResponse
            i++
            sleep(1000);
        }
        
        
        redirect uri : "/"
        
    }
}
