
<%@ page import="llc.web.scheduler.importing.ImportAttempt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'importAttempt.label', default: 'ImportAttempt')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>

      <div class="nav">
        <div class="btn-group">
          <g:link action="configure" id="${importAttemptInstance.id}" class="btn btn-default">Define Excel Configuration</g:link>
          <g:link action="executeImport" id="${importAttemptInstance.id}" class="btn btn-primary">Execute Import</g:link>  
        </div>
        <div class="pull-right">
          <g:form url="[resource:importAttemptInstance, action:'delete']" method="DELETE" class="form-inline" role="form">
            <g:actionSubmit class="btn btn-danger" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
          </g:form>
        </div>
      </div>


        <div id="show-importAttempt" class="content scaffold-show" role="main">
            <h1>Import Attempt</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <table class="table">
                <tr>
                    <td>Excel Data Source</td>
                    <td>${importAttemptInstance.ufile.name}</td>
                </tr>
                <tr>
                    <td>Excel Sheet Name</td>
                    <td>${importAttemptInstance.sheetName}</td>
                </tr>
                <tr>
                    <td>Excel Start Row</td>
                    <td>${importAttemptInstance.startRow}</td>
                </tr>
                <tr>
                    <td>Excel Configuration</td>
                    <td>${importAttemptInstance.excelMapping}</td>
                </tr>
                </table>

            <g:if test="${importAttemptInstance?.events}">
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr>
                            <th colspan="4" class="text-center success">SUCCESSFULLY IMPORTED EVENTS</th>
                        </tr>
                        <tr>
                            <th>Time</th>
                            <th>Title</th>
                            <th>Location</th>
                            <th>Description</th>
                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${importAttemptInstance.events?.sort { it.startTime }}" var="e">
                            <tr>
                                <td>${e.startTime}</td>
                                <td>${e.title}</td>
                                <td>${e.location}</td>
                                <td>${e.description}</td>
                            </tr>
                        </g:each>
                    </tbody>					
                </table>
            </g:if>

            <g:if test="${importAttemptInstance?.failedEvents}">
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr>
                            <th colspan="4" class="text-center danger">FAILED IMPORTED EVENTS</th>
                        </tr>
                        <tr>
                                <g:each in="${importAttemptInstance.excelMapping?.sort()}" var="mapping">
                                <th>${mapping.googleField}</th>    
                                </g:each>
                    
                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${importAttemptInstance.failedEvents}" var="e">
                            <tr>
                                <g:each in="${importAttemptInstance.excelMapping?.sort()}" var="mapping">
                                <td>${e.data[mapping.googleField]}</td>
                                </g:each>
                                
                            </tr>
                        </g:each>
                    </tbody>					
                </table>
            </g:if>
        </div>
    </body>
</html>
