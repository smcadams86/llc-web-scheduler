
<%@ page import="llc.web.scheduler.importing.RawCalendarDomainEvent" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'rawCalendarDomainEvent.label', default: 'RawCalendarDomainEvent')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-rawCalendarDomainEvent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-rawCalendarDomainEvent" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="comments" title="${message(code: 'rawCalendarDomainEvent.comments.label', default: 'Comments')}" />
					
						<g:sortableColumn property="date" title="${message(code: 'rawCalendarDomainEvent.date.label', default: 'Date')}" />
					
						<g:sortableColumn property="event" title="${message(code: 'rawCalendarDomainEvent.event.label', default: 'Event')}" />
					
						<th><g:message code="rawCalendarDomainEvent.importAttempt.label" default="Import Attempt" /></th>
					
						<g:sortableColumn property="minister" title="${message(code: 'rawCalendarDomainEvent.minister.label', default: 'Minister')}" />
					
						<g:sortableColumn property="time" title="${message(code: 'rawCalendarDomainEvent.time.label', default: 'Time')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${rawCalendarDomainEventInstanceList}" status="i" var="rawCalendarDomainEventInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${rawCalendarDomainEventInstance.id}">${fieldValue(bean: rawCalendarDomainEventInstance, field: "comments")}</g:link></td>
					
						<td>${fieldValue(bean: rawCalendarDomainEventInstance, field: "date")}</td>
					
						<td>${fieldValue(bean: rawCalendarDomainEventInstance, field: "event")}</td>
					
						<td>${fieldValue(bean: rawCalendarDomainEventInstance, field: "importAttempt")}</td>
					
						<td>${fieldValue(bean: rawCalendarDomainEventInstance, field: "minister")}</td>
					
						<td>${fieldValue(bean: rawCalendarDomainEventInstance, field: "time")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${rawCalendarDomainEventInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
