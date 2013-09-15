<%@ page import="llc.web.scheduler.importing.RawCalendarDomainEvent" %>



<div class="fieldcontain ${hasErrors(bean: rawCalendarDomainEventInstance, field: 'comments', 'error')} ">
	<label for="comments">
		<g:message code="rawCalendarDomainEvent.comments.label" default="Comments" />
		
	</label>
	<g:textField name="comments" value="${rawCalendarDomainEventInstance?.comments}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rawCalendarDomainEventInstance, field: 'date', 'error')} ">
	<label for="date">
		<g:message code="rawCalendarDomainEvent.date.label" default="Date" />
		
	</label>
	<g:textField name="date" value="${rawCalendarDomainEventInstance?.date}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rawCalendarDomainEventInstance, field: 'event', 'error')} ">
	<label for="event">
		<g:message code="rawCalendarDomainEvent.event.label" default="Event" />
		
	</label>
	<g:textField name="event" value="${rawCalendarDomainEventInstance?.event}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rawCalendarDomainEventInstance, field: 'importAttempt', 'error')} required">
	<label for="importAttempt">
		<g:message code="rawCalendarDomainEvent.importAttempt.label" default="Import Attempt" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="importAttempt" name="importAttempt.id" from="${llc.web.scheduler.importing.ImportAttempt.list()}" optionKey="id" required="" value="${rawCalendarDomainEventInstance?.importAttempt?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rawCalendarDomainEventInstance, field: 'minister', 'error')} ">
	<label for="minister">
		<g:message code="rawCalendarDomainEvent.minister.label" default="Minister" />
		
	</label>
	<g:textField name="minister" value="${rawCalendarDomainEventInstance?.minister}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rawCalendarDomainEventInstance, field: 'time', 'error')} ">
	<label for="time">
		<g:message code="rawCalendarDomainEvent.time.label" default="Time" />
		
	</label>
	<g:textField name="time" value="${rawCalendarDomainEventInstance?.time}"/>
</div>

