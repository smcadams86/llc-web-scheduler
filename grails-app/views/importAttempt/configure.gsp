<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="llc.web.scheduler.importing.ImportAttempt" %>
<%@ page import="llc.web.scheduler.importing.GoogleCalendarDomainEvent" %>
<%@ page import="org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass" %>


<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'importAttempt.label', default: 'ImportAttempt')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
        <g:javascript>
            var rows = ${importAttemptInstance.excelMapping?.size() > 0 ? importAttemptInstance.excelMapping?.size() : 0}
            function addRow() {
            $("#google_calendar_mapping > tr:first").clone().find("select").each(function() {
            $(this).attr({
            'id': function(_, id) { return id.replace(/0/g, rows)},
            'name': function(_, name) { return name.replace(/0/g, rows) },
            'value': ''               
            });
            }).end().appendTo("#google_calendar_mapping");
            $("#google_calendar_mapping > tr:last > td:last").append(
            $("<button>")
            .attr("type", "button")
            .attr("class", "btn btn-danger")
            .attr("onClick", "$(this).closest('tr').remove()")
            .append(
            $("<p>").attr("class", "glyphicon glyphicon-trash"))
                );
                rows++;
                }
            </g:javascript>
            </head>
        <body>
            <%
    def d = new DefaultGrailsDomainClass(GoogleCalendarDomainEvent.class)
    d.persistentProperties
    def googleFields = d.persistentProperties*.name
    googleFields.remove("importAttempt")
%>

            <h1>Map Excel Columns to Google Calendar Fields</h1>
            ${importAttemptInstance.sheetName}
            ${importAttemptInstance.id}
            <g:form name="configure" action="save" id="${importAttemptInstance.id}" class="form-horizontal" role="form">

                <div class="form-group">
                    <label for="inputSheet" class="col-lg-2 control-label">Sheet Name</label>
                    <div class="col-lg-10">
                        <g:textField class="form-control" id="inputSheet" placeholder="Sheet" name="sheetName" value="${importAttemptInstance.sheetName}" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputStartRow" class="col-lg-2 control-label">Start Row</label>
                    <div class="col-lg-10">
                        <input type="number" class="form-control" id="inputStartRow" placeholder="Row #" name="startRow"  value="${importAttemptInstance.startRow}" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="columnMap" class="col-lg-2 control-label">Column Map</label>
                    <div class="col-lg-offset-2 col-lg-10">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Excel Column</th>
                                    <th>Google Calendar Field</th>
                                </tr>
                            </thead>
                            <tbody id="google_calendar_mapping">
                                <g:if test="${importAttemptInstance.excelMapping?.size() > 0}">
                                    <g:each in="${importAttemptInstance.excelMapping}" var="map" status="i">
                                        <tr>
                                            <td><g:select name="excelMapping[${i}].columnName" value="${map.columnName}" from="${'A'..'Z'}" noSelection="['':'-Choose Excel Column-']"/></td>
                                            <td><g:select name="excelMapping[${i}].googleField"  value="${map.googleField}" from="${googleFields}" noSelection="['':'-Choose Google Field-']"/></td>
                                            <td>&nbsp;</td>
                                        </tr>
                                    </g:each>
                                </g:if>
                                <g:else>
                                    <tr>
                                        <td><g:select name="excelMapping[0].columnName" from="${'A'..'Z'}" noSelection="['':'-Choose Excel Column-']"/></td>
                                        <td><g:select name="excelMapping[0].googleField" from="${googleFields}" noSelection="['':'-Choose Google Field-']"/></td>
                                        <td>&nbsp;</td>
                                    </tr>
                                </g:else>
                            </tbody>
                        </table>
                        <div class="text-right">
                            <button type="button" class="btn btn-default" onClick="addRow();">Add Column</button>                        
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <button type="submit" class="btn btn-primary">Save Configuration</button>
                    </div>
                </div>

            </g:form>


        </body>
</html>
