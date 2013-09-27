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

  <div class="container">
    <div class="page-header">
      <h1>Export Data to Google Calendar</h1>
    </div>
    <g:if test="${createCalendarMessage}">
      <div class="${createCalendarSuccess ? 'alert alert-info' : 'alert alert-danger'}">
        Calendar Creation ${createCalendarSuccess ? 'success' : 'failure'} : ${createCalendarMessage}
      </div>
    </g:if>
    <g:if test="${importAttemptInstance?.events}">
      <div class="row">
        <form class="form-horizontal" role="form">
          <div class="form-group">
            <label for="inputCalendarSelect" class="col-lg-2 control-label">Google Calendar</label>
            <div class="col-lg-8">
              <g:select
                class="form-control"
                id="inputCalendarSelect"
                name='calendar_id'
                noSelection="${['null':'Select One...']}"
                from='${calendars}'
                optionKey="id" optionValue="${{it.summary + (it.description ? ' : ' + it.description : '')}}"></g:select>
            </div>
            <div class="col-lg-2">
              <a data-toggle="modal" href="#newCalendarModal" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-plus-sign"></span> New Calendar</a>
            </div>
          </div>
          <div class="form-group">
           <label for="sendNotifications" class="col-lg-2 control-label">Send Notifications</label>
           <div class="col-lg-8">
             <g:checkbox name="sendNotifications" value="${true}" />
           </div>
          <div class="well well-lg text-right">
            <g:submitButton name="copyToCalendar" value="Export to Google" class="btn btn-primary btn-lg" />
          </div>
          </div>
        </form>
      </div>
      <div class="page-header">
        <g:render template='/shared/dataTable'  model='[datatablesTitle : "Events To Export"]' />
      </div>
    </g:if>
    <g:else>
      <div class="alert alert-danger">
        No successful data imported. Please verify import errors have been corrected.
      </div>
      <g:link action="show" id="${importAttemptInstance.id}" class="btn btn-primary">
        Go Back
      </g:link>
    </g:else>
  </div>

  <!-- Modal -->
  <div class="modal fade" id="newCalendarModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title">Create A New Google Calendar</h4>
        </div>
        <g:form name="newCalendar" action="newCalendar" id="${importAttemptInstance.id}">
          <div class="modal-body">
            <div class="form-group">
              <label for="summaryInput">Calendar Name</label>
              <input type="text" class="form-control" id="summaryInput" placeholder="Google Calendar Summary" name="summary">
            </div>
            <div class="form-group">
              <label for="timezoneInput">Timezone</label>
              <g:timeZoneSelect name="timezone" class="form-control" />
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <!--
            <button type="button" class="btn btn-primary" onClick="createNewCalendar();">Create Calendar</button>
            -->
            <g:submitButton class="btn btn-primary" name="createCalendar" value="Create Calendar" />
          </div>
        </g:form>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->

</body>
</html>
