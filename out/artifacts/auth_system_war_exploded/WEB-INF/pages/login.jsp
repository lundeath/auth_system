<%--
  Created by IntelliJ IDEA.
  User: yurii.ukrainets
  Date: 10/5/2018
  Time: 10:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/login_page.css">
    <script src="/js/fieldValidation.js" defer></script>
</head>
<body>
<div class="login-page">
    <div class="form">
        <h3>Login</h3>
        <form  name="loginForm" action="/login" method="post" class="login-form">
            <input type="text" name="login" placeholder="Login" data-rule="required"><br>
            <span class="reqMsg" id="firstN">* First name is required</span><br>
            <input type="password" name="password" placeholder="Password" data-rule="required"><br>
            <span class="reqMsg" id="lastN">* Last name is required</span><br>
            Choose department:
            <select name="department" data-rule="required">
                <option selected value="1">01</option>
                <option value="2">02</option>
                <option value="3">03</option>
                <option value="4">04</option>
                <option value="5">05</option>
                <option value="6">06</option>
                <option value="7">07</option>
                <option value="8">08</option>
            </select><br>
            <p class="wrongMes">${message}</p>
            <input type="submit" value="Submit"/><br>
        </form>
    </div>
</div>
</body>
</html>
