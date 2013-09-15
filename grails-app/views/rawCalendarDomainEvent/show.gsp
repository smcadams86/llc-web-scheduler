
<%@ page import="llc.web.scheduler.importing.RawCalendarDomainEvent" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'rawCalendarDomainEvent.label', default: 'RawCalendarDomainEvent')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-rawCalendarDomainEvent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-rawCalendarDomainEvent" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list rawCalendarDomainEvent">
			
				<g:if test="${rawCalendarDomainEventInstance?.comments}">
				<li class="fieldcontain">
					<span id="comments-label" class="property-label"><g:message code="rawCalendarDomainEvent.comments.label" default="Comments" /></span>
					
						<span class="property-value" aria-labelledby="comments-label"><g:fieldValue bean="${rawCalendarDomainEventInstance}" field="comments"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rawCalendarDomainEventInstance?.date}">
				<li class="fieldcontain">
					<span id="date-label" class="property-label"><g:message code="rawCalendarDomainEvent.date.label" default="Date" /></span>
					
						<span class="property-value" aria-labelledby="date-label"><g:fieldValue bean="${rawCalendarDomainEventInstance}" field="date"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rawCalendarDomainEventInstance?.event}">
				<li class="fieldcontain">
					<span id="event-label" class="property-label"><g:message code="rawCalendarDomainEvent.event.label" default="Event" /></span>
					
						<span class="property-value" aria-labelledby="event-label"><g:fieldValue bean="${rawCalendarDomainEventInstance}" field="event"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rawCalendarDomainEventInstance?.importAttempt}">
				<li class="fieldcontain">
					<span id="importAttempt-label" class="property-label"><g:message code="rawCalendarDomainEvent.importAttempt.label" default="Import Attempt" /></span>
					
						<span class="property-value" aria-labelledby="importAttempt-label"><g:link controller="importAttempt" action="show" id="${rawCalendarDomainEventInstance?.importAttempt?.id}">${rawCalendarDomainEventInstance?.importAttempt?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${rawCalendarDomainEventInstance?.minister}">
				<li class="fieldcontain">
					<span id="minister-label" class="property-label"><g:message code="rawCalendarDomainEvent.minister.label" default="Minister" /></span>
					
						<span class="property-value" aria-labelledby="minister-label"><g:fieldValue bean="${rawCalendarDomainEventInstance}" field="minister"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rawCalendarDomainEventInstance?.time}">
				<li class="fieldcontain">
					<span id="time-label" class="property-label"><g:message code="rawCalendarDomainEvent.time.label" default="Time" /></span>
					
						<span class="property-value" aria-labelledby="time-label"><g:fieldValue bean="${rawCalendarDomainEventInstance}" field="time"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:rawCalendarDomainEventInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${rawCalendarDomainEventInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
