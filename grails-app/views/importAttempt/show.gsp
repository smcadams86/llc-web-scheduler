
<%@ page import="llc.web.scheduler.importing.ImportAttempt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'importAttempt.label', default: 'ImportAttempt')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-importAttempt" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                </ul>
            </div>

        <div class="btn-group">
            <g:link action="configure" class="btn btn-default">Define Excel Configuration</g:link>
                <g:link action="executeImport" id="${importAttemptInstance.id}" class="btn btn-default">Execute Import</g:link>
                <button type="button" class="btn btn-default">Right</button>
            </div>


        <div id="show-importAttempt" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
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
                                <th>${mapping.value}</th>    
                                </g:each>
                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${importAttemptInstance.failedEvents}" var="e">
                            <tr>
                                <g:each in="${importAttemptInstance.excelMapping?.sort()}" var="mapping">
                                <td>${e[mapping.value]}</td>
                                </g:each>
                                
                            </tr>
                        </g:each>
                    </tbody>					
                </table>
            </g:if>
            <g:form url="[resource:importAttemptInstance, action:'delete']" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${importAttemptInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
