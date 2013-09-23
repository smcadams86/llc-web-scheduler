<%@ page import="llc.web.scheduler.importing.GoogleCalendarDomainEvent" %>
<%@ page import="org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass" %>


<%
        def d = new DefaultGrailsDomainClass(GoogleCalendarDomainEvent.class)
        
        def googleFields = d.persistentProperties*.name
        googleFields.remove("importAttempt")
    %>
    
    <table id="events_table" class="table table-striped table-bordered table-hover">
      <thead>
        <tr>
          <th colspan="${googleFields.size()}" class="text-center success">${datatablesTitle}</th>
        </tr>
        <tr>
          <g:each in="${googleFields}" var="field">
            <th>${field}</th>
          </g:each>
        </tr>
      </thead>
      <tbody>
      <g:each in="${importAttemptInstance.events?.sort { it.startTime }}" var="e">
        <tr>
          <g:each in="${googleFields}" var="field">
            <td>${e."${field}"}</td>
          </g:each>
        </tr>
      </g:each>
      </tbody>					
    </table>
    <script>
      $(document).ready(function() {
        $("#events_table").dataTable( {
            "aaSorting": [[ 0, "desc" ]],
            "sPaginationType": "bs_normal",
            "bPaginate": false
        });
        $("select").addClass("form-control");
      });
    </script>