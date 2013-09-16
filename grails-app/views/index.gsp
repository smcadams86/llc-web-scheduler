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
                <p>
                    <a class="btn btn-primary btn-large"> Get Started! </a>
                </p>
            </div>

            <div class="row">
                <div class="col-md-3">
                    <div class="well well-lg">
                        <h3>Upload Excel File</h3>
                        <p>
                            <fileuploader:form upload="excel" successAction="success"
                            successController="upload" errorAction="error"
                            errorController="upload" />

                            <g:javascript>
                                $(":input[name='file']").width("100%");
                                $(":input[name='file']").addClass("btn btn-default");
                                $(":input[name='submit']").addClass("btn btn-primary");
                                $(":input[name='submit']").width("100%");
                            </g:javascript>
                        </p>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="well well-lg">
                        <h3>Authorize Google Calendar API</h3>
                        <p>
                        <oauth:connect provider="google" id="google-connect-link" class="btn btn-primary">Google Authorization</oauth:connect>
                        </p>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="well well-lg">
                        <h3>Verify Imported Data</h3>
                        <p>
                            <g:link controller="importAttempt" class="btn btn-primary">Import Attempt List</g:link>
                            </p>
                        </div>
                    </div>

                <div class="col-md-3">
                    <div class="well well-lg">
                        <h3>Select Google Calendar Export</h3>
                        <p>
                            <g:link controller="calendar" action="show"  class="btn btn-primary">Google Calendar List</g:link>
                            </p>
                        </div>
                    </div>

            </div>




            <div id="controller-list" role="navigation">
                <h2>Available Controllers:</h2>
                <ul>
                    <g:each var="c"
                    in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                        <li class="controller"><g:link
                            controller="${c.logicalPropertyName}">
                                ${c.fullName}
                            </g:link></li>
                        </g:each>
                </ul>
            </div>
        </div>
    </body>
</html>
