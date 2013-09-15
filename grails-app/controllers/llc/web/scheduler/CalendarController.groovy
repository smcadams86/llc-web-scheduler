package llc.web.scheduler

import uk.co.desirableobjects.oauth.scribe.OauthService
import grails.converters.*
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.http.HttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory


class CalendarController {

	OauthService oauthService
	def grailsApplication

	private static final HttpTransport TRANSPORT = new NetHttpTransport();
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();

	def index() {
	}


	def getGoogleAccessToken() {
		String sessionKey = oauthService.findSessionKeyForAccessToken('google')
		return session[sessionKey]
	}


	def show(){
		if (getGoogleAccessToken()) {


			def calendars = oauthService.getGoogleResource(getGoogleAccessToken(), 'https://www.googleapis.com/calendar/v3/users/me/calendarList?key=${grailsApplication.config.oauth.providers.google.key}')
			def calendarsJSON = JSON.parse(calendars.body)

			def sb = new StringBuilder()
			sb.append(calendarsJSON.etag + "<br />")
			sb.append(calendarsJSON.items?.id + "<br />")

			def calendarList = []
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

			[calendars : calendarList]
		}
		else {
			redirect (uri :"/oauth/google/authenticate")
		}

		//        GoogleCredential credential = new GoogleCredential().setAccessToken(getGoogleAccessToken());
		//        // Set up the main Google+ class
		//        com.google.api.services.calendar.Calendar client = new com.google.api.services.calendar.Calendar.Builder(
		//        TRANSPORT, JSON_FACTORY, credential).setApplicationName("llc-web-scheduler/1.0")
		//        .build();
		//
		//
		//        // Make a request to access your profile and display it to console
		//        def cList = client.calendarList().
		//        def profile = plus.people().get("me").execute();
		//        System.out.println("ID: " + profile.getId());
		//        System.out.println("Name: " + profile.getDisplayName());
		//        System.out.println("Image URL: " + profile.getImage().getUrl());
		//        System.out.println("Profile URL: " + profile.getUrl());

	}
}
