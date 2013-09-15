package llc.web.scheduler

class CallbackController {
    def oauthService
    def googleCalendarService
    

    def index() { }
    
    def success() {
        println "success : ${params}"
        String sessionKey = oauthService.findSessionKeyForAccessToken('google')
        def scribeToken = session[sessionKey]
        
//        println session[sessionKey]
//        googleCalendarService.list(scribeToken.token)
        
        redirect controller:'calendar', action:'show'
    }
    
    def failure() {
        
    }
}
