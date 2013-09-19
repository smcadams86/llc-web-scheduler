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
        
        
            <g:if test="${importAttemptInstance?.events}">
                
                <form class="form-horizontal" role="form">
  <div class="form-group">
    <label for="inputCalendarSelect" class="col-lg-2 control-label">Google Calendar</label>
    <div class="col-lg-10">
        <g:select
            class="form-control"
  id="inputCalendarSelect"
  name='calendar_id'
    noSelection="${['null':'Select One...']}"
    from='${calendars}'
    optionKey="id" optionValue="${{it.summary + (it.description ? ' : ' + it.description : '')}}"></g:select>
  
    </div>
  </div>
  <div class="form-group">
    <label for="selectedData" class="col-lg-2 control-label">Selected Data</label>
    <div class="col-lg-10">
        
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
                      "aaSorting": [[ 1, "desc" ]]
                  });
                  $("select").addClass("form-control");
                });
              </script>
              
              </div>
              </div>
                  <g:submitButton name="copyToCalendar" value="Export to Google" class="btn btn-primary btn-lg pull-right" />
                </form>
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
  </div>
                </div>
        
    </body>
</html>
