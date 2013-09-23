<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>LLC Web Scheduler</title>
  </head>
  <body>
    <div class="container">

      <div class="jumbotron">
        <h1>LLC Web Scheduler</h1>
        <p>Taking the pain out of scheduling</p>
      </div>
      <div class="row">
        <g:if test="${flash.message}">
          <div class="alert alert-info" role="status">${flash.message}</div>
        </g:if>
      </div>
      <g:if test="${session['google:oasAccessToken']}">
        <div class="row">
          <div class="text-center">
            <h2>Pick Your Data Source</h2>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="well well-lg">
              <h3>Upload Excel File</h3>
              <p>
              <fileuploader:form upload="excel" successAction="success"
                                 successController="upload" errorAction="error"
                                 errorController="upload" />
                <g:javascript>
                  $(":input[name='file']").addClass("btn btn-default btn-block ");
                  $(":input[name='submit']").addClass("btn btn-primary btn-block");
                </g:javascript>
              </p>
            </div>
          </div>
          <div class="col-md-6">
            <div class="well well-lg">
              <h3>Copy from Google Calendar</h3>
              <p>
              <oauth:connect provider="google" id="google-connect-link" class="btn btn-primary btn-block">Google Authorization</oauth:connect>
              </p>
            </div>
          </div>
        </div>
      </g:if>
      <g:else>
        <div class="well well-lg">
          <oauth:connect provider="google" id="google-connect-link" class="btn btn-primary btn-block">Google Authorization</oauth:connect>
        </div>
      </g:else>
    </div>
  </div>
</body>
</html>
