package llc.web.scheduler

import grails.transaction.Transactional
import com.google.api.client.*
import com.google.api.services.calendar.*
import grails.converters.*
import java.net.URLEncoder

import org.scribe.builder.*;
import org.scribe.builder.api.*;
import org.scribe.model.*;
import org.scribe.oauth.*;
import com.google.api.services.calendar.*


import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import com.google.api.client.http.GenericUrl
import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.Calendar.CalendarList

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.services.calendar.model.CalendarListEntry
import llc.web.scheduler.importing.ImportAttempt

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
    
//    public static HttpResponse executeGet(
//        HttpTransport transport, JsonFactory jsonFactory, String accessToken, GenericUrl url)
//    throws IOException {
//        Credential credential =
//        new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);
//        HttpRequestFactory requestFactory = transport.createRequestFactory(credential);
//        return requestFactory.buildGetRequest(url).execute();
//    }
    
    def create(token, summary, timezone) {
        println "create($summary, $timezone)"
        
        if (token) {
            def url = "https://www.googleapis.com/calendar/v3/calendars?fields=Csummary=${summary}%2CtimeZone=${timezone}&key=${grailsApplication.config.oauth.providers.google.key}"
            def listUrl = 'https://www.googleapis.com/calendar/v3/users/me/calendarList?key=${grailsApplication.config.oauth.providers.google.key}'
            
            
            Map conf = fetchConfig()
            println "conf.providers.google : ${conf.providers.google}"
            
            
//            
//  GoogleAccountCredential credential;
//  CalendarModel model = new CalendarModel();
//  com.google.api.services.calendar.Calendar client;
//            
//            client = new com.google.api.services.calendar.Calendar.Builder(
//        transport, jsonFactory, credential).setApplicationName("Google-CalendarAndroidSample/1.0")
//        .build();
//            
//            
//            
//            
            
        
                
//            HttpResponse httpResponse = executeGet(
//                transport,
//                jsonFactory,
//                token.token,
//                new GenericUrl(listUrl))
//            println "httpResponse : ${httpResponse}"
        
//		GoogleAccessProtectedResource accessProtectedResource = new GoogleAccessProtectedResource(
//				token.token, httpTransport, jsonFactory, clientId,
//				clientSecret, response.refreshToken);
//
//            
//            HttpTransport httpTransport = new NetHttpTransport();
//            JacksonFactory jsonFactory = new JacksonFactory();
//            GoogleCredential credential = new GoogleCredential().setAccessToken(token.token);
//            com.google.api.services.calendar.Calendar service = new com.google.api.services.calendar.Calendar(httpTransport, jsonFactory,
//				credential);
            
//            
//            
//            com.google.api.services.calendar.Calendar client = com.google.api.services.calendar.Calendar.builder(httpTransport, jsonFactory)
//                .setApplicationName("llcWebScheduler/1.0")
//                .setJsonHttpRequestInitializer(credential)
//                .build();
//                
//            CalendarList feed = client.calendarList().list().execute();
//            println "feed : ${feed}"
    
            
//            println "service.calendarList().list().execute() : " + service.calendarList().list().execute()
//            
//            com.google.api.services.calendar.model.Calendar calendar = new com.google.api.services.calendar.model.Calendar();
//            calendar.setSummary(summary);
//            calendar.setTimeZone(timezone);
//            com.google.api.services.calendar.model.Calendar createdCalendar = service.calendars().insert(calendar).execute();
//
//            System.out.println(createdCalendar.getId());
//            
//            
//            
//            println "oauthService : $oauthService"
//            println "oauthService.services : ${oauthService.services }"
//            def googleProvider = oauthService.services['google']
//            def googleService = googleProvider.service
//            
//            println "googleService : $googleService"
//            
//             println "-" * 10
//            OAuthRequest oauthListRequest = new OAuthRequest(Verb.GET, listUrl);
//            googleService.signRequest(token, oauthListRequest);
//            
//            
//    Response listResponse = oauthListRequest.send();
//    System.out.println("Got it! Lets see what we found...");
//    System.out.println();
//    System.out.println(listResponse.getCode());
//    System.out.println(listResponse.getBody());
//            
//            println "-" * 10
//            
//            
//            
//            println "googleService.getRequestToken() : ${googleService.getRequestToken()}"
//                        
//            // Now let's go and ask for a protected resource!
//    System.out.println("Now we're going to access a protected resource...");
//    OAuthRequest oauthRequest = new OAuthRequest(Verb.POST, url);
////    oauthRequest.addQuerystringParameter("access_token", token.token)
//    
//    oauthRequest.addHeader("Content-type", "application/json");
//    oauthRequest.addHeader("Authorization", "Bearer ${token.token}");
//    oauthRequest.addBodyParameter("summary", summary)
//    oauthRequest.addBodyParameter("timeZone", timezone)
//    googleService.signRequest(token, oauthRequest);
//    
//        println "getQueryStringParams : ${oauthRequest.getQueryStringParams().collect { it.asFormUrlEncodedString() }}"
//    println "getBodyParams : ${oauthRequest.getBodyParams().collect { it.asFormUrlEncodedString() }}"
//    println "getBodyContents : ${oauthRequest.getBodyContents()}"
//    println "getHeaders : ${oauthRequest.getHeaders()}"
//    println "oauthRequest : ${oauthRequest}"
//            
//    Response response = oauthRequest.send();
//    System.out.println("Got it! Lets see what we found...");
//    System.out.println();
//    System.out.println(response.getCode());
//    System.out.println(response.getBody());
            
            
            def getCalendarUrl = "https://www.googleapis.com/calendar/v3/calendars/5nbpkdua0hk3nbs0lphf5igsps@group.calendar.google.com"
            println oauthService.getGoogleResource(token, listUrl).body
            println oauthService.getGoogleResource(token, getCalendarUrl).body

            
            def calendar = oauthService.postGoogleResource(token, url)
            def jsonResponse = JSON.parse(calendar.body)
            println calendar.body
            println "=" * 20
            println "jsonResponse = $jsonResponse"
            println "=" * 20
            
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
