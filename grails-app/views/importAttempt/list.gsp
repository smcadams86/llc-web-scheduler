<%@ page import="llc.web.scheduler.importing.ImportAttempt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'importAttempt.label', default: 'ImportAttempt')}" />
  <title><g:message code="default.show.label" args="[entityName]" /></title>
  <link rel="shortcut icon" href="" type="image/x-icon">
  <script src="${resource(dir: 'js/datatables/media/js', file: 'jquery.dataTables.min.js')}"></script>
  <link rel="stylesheet" href="${resource(dir : 'js/datatables-bootstrap/css', file : 'datatables.css')}" type="text/css"/>
  <script src="${resource(dir: 'js/datatables-bootstrap/js', file: 'datatables.js')}"></script>
</head>
<body>

	<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>File Name</th>
						<th>Sheet Name</th>
						<th>Date Created</th>
						<th>Starting Row</th>
						<th># Imported</th>
						<th># Failed</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${ImportAttempt.list()}" status="i" var="importAttemptInstance">
					<tr>
						<td>${importAttemptInstance.ufile?.name}</td>
						<td>${importAttemptInstance.sheetName}</td>
						<td><g:formatDate date="${importAttemptInstance.dateCreated}" format="MM/dd/yyyy HH:mm"/></td>
						<td>${importAttemptInstance.startRow}</td>
						<td class="success">${importAttemptInstance.events?.size()}</td>
						<td class="danger">${importAttemptInstance.failedEvents?.size()}</td>
						<td><g:link action="show" id="${importAttemptInstance.id}"><span class="btn btn-default">Show <span class="glyphicon glyphicon-chevron-right"></span></span></g:link></td>
					</tr>
				</g:each>
				</tbody>
			</table>
</body>
</html>