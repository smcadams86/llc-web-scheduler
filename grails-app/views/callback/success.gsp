<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Authentication Success</title>
    </head>
    <body>
        <h1>Success</h1>
    <sec:loggedInUserInfo field="username"/>
    <sec:ifLoggedIn>
        Welcome Back <sec:username/>!
    </sec:ifLoggedIn>
    <sec:ifNotLoggedIn>
        <g:link controller='login' action='auth'>Login</g:link>
        </sec:ifNotLoggedIn>
    </body>
</html>
