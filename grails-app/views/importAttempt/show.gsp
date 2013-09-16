
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

<%
def isEmpty = { c ->
  return c == null || c.size() == 0
}
%>

  <h1>Import Attempt</h1>
  <g:if test="${flash.message}">
    <div class="alert alert-info" role="status">${flash.message}</div>
  </g:if>
  
  
  
  <div class="btn-toolbar">
    <div class="btn-group btn-group-lg">
      <g:link action="configure" id="${importAttemptInstance.id}" class="btn btn-default">
        Define Excel Configuration
      </g:link>
      <g:link action="executeImport" id="${importAttemptInstance.id}"  class="btn btn-default" >
        Execute Import
      </g:link>
      <g:link action="executeExport" id="${importAttemptInstance.id}" class="btn btn-default" >
        Execute Export
      </g:link>
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
        <input type="number" class="form-control" id="startRow" placeholder="Excel Start Row"  value="${importAttemptInstance.startRow}"  disabled="disabled">
      </div>
      <div class="col-lg-1">
        <div class="btn btn-default"><span class="glyphicon glyphicon-edit" /></div>
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
    <table id="events_table" class="table table-striped table-bordered table-hover">
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
    <script>
      $(document).ready(function() {
        $("#events_table").dataTable( {
            "aaSorting": [[ 1, "desc" ]],
            "sPaginationType": "bs_normal"
        });
        $("select").addClass("form-control");
      });
    </script>
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
          <td>${e.failedFields.find { it.columnName.equals(map.googleField)}.value}</td>
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
