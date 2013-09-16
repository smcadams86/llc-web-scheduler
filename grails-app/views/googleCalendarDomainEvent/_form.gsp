<%@ page import="llc.web.scheduler.importing.GoogleCalendarDomainEvent" %>



<div class="fieldcontain ${hasErrors(bean: googleCalendarDomainEventInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="googleCalendarDomainEvent.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${googleCalendarDomainEventInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: googleCalendarDomainEventInstance, field: 'guests', 'error')} ">
	<label for="guests">
		<g:message code="googleCalendarDomainEvent.guests.label" default="Guests" />
		
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: googleCalendarDomainEventInstance, field: 'location', 'error')} ">
	<label for="location">
		<g:message code="googleCalendarDomainEvent.location.label" default="Location" />
		
	</label>
	<g:textField name="location" value="${googleCalendarDomainEventInstance?.location}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: googleCalendarDomainEventInstance, field: 'startTime', 'error')} required">
	<label for="startTime">
		<g:message code="googleCalendarDomainEvent.startTime.label" default="Start Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="startTime" precision="day"  value="${googleCalendarDomainEventInstance?.startTime}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: googleCalendarDomainEventInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="googleCalendarDomainEvent.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${googleCalendarDomainEventInstance?.title}"/>
</div>

