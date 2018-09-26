<%--
  Created by IntelliJ IDEA.
  User: Fenyr_Allen
  Date: 2018-09-26
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="./style/register.css">
</head>
<body>
<div class="reg">
    <div class="header">
        <h1>
            <a href="./login.jsp">Login</a> <a href="./register.jsp">Register</a>
        </h1>
    </div>
    <!--
        Conditions for file uploading:
        * method = post
        * name and value
        * enctype = multipart/form-data
     -->
    <%
        String message = "";
        if (request.getAttribute("message") != null) {
            message = (String) request.getAttribute("message");
        }
    %>
    <h2 style="color: red;"><%=message%></h2>
    <form action="RegisterServlet" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td class="td1">Username:</td>
                <td><input type="text" class="input1" name="username"></td>
            </tr>
            <tr>
                <td class="td1">Password:</td>
                <td><input type="password" class="input1" name="password"></td>
            </tr>
            <tr>
                <td class="td1">Nickname:</td>
                <td><input type="text" class="input1" name="nickname"></td>
            </tr>
            <tr>
                <td class="td1">Gender:</td>
                <td>
                    <input type="radio" name="gender" value="male"> 男
                    <input type="radio" name="gender" value="female"> 女
                </td>
            </tr>
            <tr>
                <td class="td1">Upload Avatar</td>
                <td><input type="file" id="photo" name="upload"></td>
            </tr>
            <tr>
                <td class="td1">Hobbies:</td>
                <td><label>
                    <input type="checkbox" name="hobby" value="Basketball"> Basketball
                    <input type="checkbox" name="hobby" value="Soccer"> Soccer
                    <input type="checkbox" name="hobby" value="Music"> Music
                </label></td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="btn-red">
                        <input type="submit" value="register" id="reg-btn">
                    </div>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>