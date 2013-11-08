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
        def newCalendarUrl = "https://www.googleapis.com/calendar/v3/calendars"

        def payload = """
        {
            summary: "brand new calendar" 
        }
        """
        
        def i = 0
        def jsonResponse = JSON.parse(oauthService.postGoogleResourceWithPayload(token, newCalendarUrl, payload, 'application/json').body)
        if (jsonResponse.error) {
            flash.message = jsonResponse
        }
        redirect uri : "/"
    }

    
}
