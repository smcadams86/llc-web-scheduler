
<%@ page import="llc.web.scheduler.importing.GoogleCalendarDomainEvent" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'googleCalendarDomainEvent.label', default: 'GoogleCalendarDomainEvent')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-googleCalendarDomainEvent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-googleCalendarDomainEvent" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list googleCalendarDomainEvent">
			
				<g:if test="${googleCalendarDomainEventInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="googleCalendarDomainEvent.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${googleCalendarDomainEventInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${googleCalendarDomainEventInstance?.guests}">
				<li class="fieldcontain">
					<span id="guests-label" class="property-label"><g:message code="googleCalendarDomainEvent.guests.label" default="Guests" /></span>
					
						<span class="property-value" aria-labelledby="guests-label"><g:fieldValue bean="${googleCalendarDomainEventInstance}" field="guests"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${googleCalendarDomainEventInstance?.location}">
				<li class="fieldcontain">
					<span id="location-label" class="property-label"><g:message code="googleCalendarDomainEvent.location.label" default="Location" /></span>
					
						<span class="property-value" aria-labelledby="location-label"><g:fieldValue bean="${googleCalendarDomainEventInstance}" field="location"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${googleCalendarDomainEventInstance?.startTime}">
				<li class="fieldcontain">
					<span id="startTime-label" class="property-label"><g:message code="googleCalendarDomainEvent.startTime.label" default="Start Time" /></span>
					
						<span class="property-value" aria-labelledby="startTime-label"><g:formatDate date="${googleCalendarDomainEventInstance?.startTime}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${googleCalendarDomainEventInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="googleCalendarDomainEvent.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${googleCalendarDomainEventInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:googleCalendarDomainEventInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${googleCalendarDomainEventInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
