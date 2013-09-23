package llc.web.scheduler

class CallbackController {
    def oauthService
    def googleCalendarService
    

    def index() { }
    
    def success() {
        flash.message = "Succesfully Authenticated!"
        redirect uri : "/"
    }
    
    def failure() {
        flash.message = "Authentication Failure"
        redirect uri : "/"
    }
}
