
<%@ page import="llc.web.scheduler.importing.GoogleCalendarDomainEvent" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'googleCalendarDomainEvent.label', default: 'GoogleCalendarDomainEvent')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-googleCalendarDomainEvent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-googleCalendarDomainEvent" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="description" title="${message(code: 'googleCalendarDomainEvent.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="location" title="${message(code: 'googleCalendarDomainEvent.location.label', default: 'Location')}" />
					
						<g:sortableColumn property="startTime" title="${message(code: 'googleCalendarDomainEvent.startTime.label', default: 'Start Time')}" />
					
						<g:sortableColumn property="title" title="${message(code: 'googleCalendarDomainEvent.title.label', default: 'Title')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${googleCalendarDomainEventInstanceList}" status="i" var="googleCalendarDomainEventInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${googleCalendarDomainEventInstance.id}">${fieldValue(bean: googleCalendarDomainEventInstance, field: "description")}</g:link></td>
					
						<td>${fieldValue(bean: googleCalendarDomainEventInstance, field: "location")}</td>
					
						<td><g:formatDate date="${googleCalendarDomainEventInstance.startTime}" /></td>
					
						<td>${fieldValue(bean: googleCalendarDomainEventInstance, field: "title")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${googleCalendarDomainEventInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
