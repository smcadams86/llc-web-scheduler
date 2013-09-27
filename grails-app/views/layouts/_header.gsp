
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<g:link uri="/" class="navbar-brand">LLC Web Scheduler</g:link>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li
					class="${'importAttempt'.equals(controllerName) ? 'active' : ''}">
					<g:link controller="importAttempt">Import Attempts</g:link>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li>
					<sec:ifLoggedIn>
						Logged in as <sec:loggedInUserInfo field="username" />
					</sec:ifLoggedIn>
					<sec:ifNotLoggedIn>
						<oauth:connect provider="google" id="google-connect-link">Google Authorization</oauth:connect>
					</sec:ifNotLoggedIn>
				</li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</div>
