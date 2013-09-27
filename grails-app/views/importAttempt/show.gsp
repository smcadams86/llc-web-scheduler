
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
  
  <g:javascript library="jquery" />
  
  
</head>
<body>

<%
def isEmpty = { c ->
  return c == null || c.size() == 0
}
%>
<div class="page-header">
  <h1>Import Attempt</h1>
  </div>
  <g:if test="${flash.message}">
    <div class="alert alert-info alert-dismissable">
      <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
      ${flash.message}
    </div>
  </g:if>
  
  <div class="btn-toolbar">
    <div class="btn-group btn-group-lg">
      <g:link action="configure" id="${importAttemptInstance.id}" class="btn btn-default">
        Define Excel Configuration
      </g:link>
      <g:link action="executeImport" id="${importAttemptInstance.id}"  class="btn btn-default" >
        Execute Import
      </g:link>
      <g:if test="${session['google:oasAccessToken']}">
      <g:link action="export" id="${importAttemptInstance.id}" class="btn btn-default" >
        Execute Export
      </g:link>
      </g:if>
      <g:else>
        <oauth:connect provider="google" id="google-connect-link" class="btn btn-default">Google Authorization</oauth:connect>
      </g:else>
    </div>
  </div>

  <p>
    <g:form url="[resource:importAttemptInstance, action:'delete']" method="DELETE" role="form" class="form-inline">
      <g:actionSubmit class="btn btn-danger" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
    </g:form>
  </p>
  
  
  <form class="form-horizontal" role="form">
    <div class="form-group">
      <label for="" class="col-lg-2 control-label">Excel Data Source</label>
      <div class="col-lg-10">
        ${importAttemptInstance.ufile?.name}
      </div>
    </div>
    <div class="form-group">
      <label for="inputSheetName" class="col-lg-2 control-label">Excel Sheet Name</label>
      <div class="col-lg-9">
        <input type="text" class="form-control" id="inputSheetName" placeholder="Excel Sheet Name" value="${importAttemptInstance.sheetName}" disabled="disabled">
      </div>
      <div class="col-lg-1">
        <div class="btn btn-default"><span class="glyphicon glyphicon-edit" /></div>
      </div>
    </div>
    <div class="form-group">
      <label for="inputStartRow" class="col-lg-2 control-label">Excel Start Row</label>
      <div class="col-lg-9">
        <div id="changeStartRowContent" style="display: none;">
          <div id="changeStartRowResponse"></div>
          <g:remoteField
            id="${importAttemptInstance.id}"
            class="form-control"
            action="changeStartRow"
            update="changeStartRowResponse"
            name="startRow"
            onSuccess="\$('#originalStartRowContent').css('display','block');\$('#changeStartRowContent').css('display','none');\$('#originalStartRowContent').addClass('alert alert-success');"
            onFailure="\$('#changeStartRowContent').css('display','block');\$('#originalStartRowContent').css('display','none');\$('#changeStartRowContent').addClass('alert alert-danger');"
            value="${importAttemptInstance.startRow}" />
        </div>
        <div id="originalStartRowContent" >
          <input type="number" class="form-control" placeholder="Excel Start Row" value="${importAttemptInstance.startRow}"  disabled="disabled">
        </div>
      </div>
      <div class="col-lg-1">
        <button type="button" class="btn btn-default" onclick="$('#changeStartRowContent').css('display','block');$('#originalStartRowContent').css('display','none'); $('#changeStartRowContent').children('input').focus();"><span class="glyphicon glyphicon-edit" /></button>
      </div>
    </div>
    <div class="form-group">
      <label for="excelMapping" class="col-lg-2 control-label">Excel Data Source</label>
      <div class="col-lg-9">
        <ul>
        <g:each in="${importAttemptInstance.excelMapping?.sort{ it.columnName }}" var="map">
          <li>${map.columnName} : ${map.googleField}</li>
        </g:each>
          </ul>
      </div>
      <div class="col-lg-1">
        <div class="btn btn-default"><span class="glyphicon glyphicon-edit" /></div>
      </div>
    </div>
  </form>
  
  <g:if test="${importAttemptInstance?.events}">
    <g:render template='/shared/dataTable' model='[datatablesTitle : "SUCCESSFULLY IMPORTED EVENTS"]' />
  </g:if>

  <g:if test="${importAttemptInstance?.failedEvents}">
    <div class="row alert alert-danger">
      <p class="text-center">FAILED IMPORTED EVENTS</p>
    </div>
    <% def mapping = importAttemptInstance.excelMapping?.sort { it.columnName } %>
    <table id="failed_events_table" class="table table-striped table-bordered table-hover">
      <thead>
        <tr>
      <g:each in="${mapping}" var="map">
        <th>${map.googleField}</th>    
      </g:each>
      <td>&nbsp;</td>
      </tr>
      </thead>
      <tbody>
      <g:each in="${importAttemptInstance.failedEvents}" var="e">
        <tr>
        <g:each in="${mapping}" var="map">
            <% def fieldError = e.failedFields.find { it.columnName.equals(map.googleField)}.error %>
          <td class="${!isEmpty(fieldError) ? 'danger' : ''}" >
            ${e.failedFields.find { it.columnName.equals(map.googleField)}.value}
          </td>
        </g:each>
        <td><div class="btn btn-default"><span class="glyphicon glyphicon-edit" /></div></td>
        </tr>
      </g:each>
      </tbody>					
    </table>
    <script>
      $(document).ready(function() {
        $("#failed_events_table").dataTable( {
            "aaSorting": [[ 0, "asc" ]],
            "sPaginationType": "bs_normal"
        });
        $("select").addClass("form-control");
      });
    </script>
  </g:if>
</body>
</html>
