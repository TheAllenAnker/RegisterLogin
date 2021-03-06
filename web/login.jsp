<%@ page import="com.allenanker.utils.CookieUtils" %><%--
  Created by IntelliJ IDEA.
  User: Fenyr_Allen
  Date: 2018-09-26
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="./style/login.css">
</head>
<body>
<div class="login">
    <div class="header">
        <h1>
            <a href="./login.jsp">Login</a> <a href="./register.jsp">Register</a>
        </h1>

    </div>
    <%
        String username = "";

        Cookie[] cookies = request.getCookies();
        Cookie cookie = CookieUtils.findCookie(cookies, "username");
        if (cookie != null) {
            username = cookie.getValue();
        }

        if (session.getAttribute("username") != null) {
            username = (String) session.getAttribute("username");
        }

        String message = "";
        if (request.getAttribute("message") != null) {
            message = (String) request.getAttribute("message");
        }
    %>
    <h2 style="color: red;"><%=message%>
    </h2>
    <form action="LoginServlet" method="post">
        <table>
            <tr>
                <td class="td1">Account:</td>
                <td><input type="text" class="input1" name="username" value="<%=username%>"></td>
            </tr>
            <tr>
                <td class="td1">Password:</td>
                <td><input type="password" class="input1" name="password"></td>
            </tr>
            <tr>
                <td class="td1" colspan="2">
                    <input type="checkbox" name="remember" value="true" checked="checked"> Remember me
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="btn-red">
                        <input type="submit" value="Login" id="login-btn">
                    </div>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>