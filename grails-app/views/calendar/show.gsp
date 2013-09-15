<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>Calendars</title>
</head>
<body>
	<div class="container">
      <table class="table table-bordered">
        <thead>
          <tr>
          <th>Summary</th>
            <th>Description</th>
            <th>ID</th>
          </tr>
        </thead>
        <tbody>
	    <g:each in="${calendars}" var="calendar">
	      <tr>
	        <td bgcolor="${calendar.backgroundColor}"><font color="${calendar.foregroundColor}">${calendar.summary}</font></td>
	        <td bgcolor="${calendar.backgroundColor}"><font color="${calendar.foregroundColor}">${calendar.description}</font></td>
	        <td bgcolor="${calendar.backgroundColor}"><font color="${calendar.foregroundColor}">${calendar.id}</font></td>
	      </tr>
	    </g:each>
	    </tbody>
	  </table>
	</div>
</body>
</html>