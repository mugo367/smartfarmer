<%--
  Created by IntelliJ IDEA.
  User: mugo367
  Date: 9/29/21
  Time: 3:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, shrink-to-fit=no, initial-scale=1">
    <title>Login | Smart Farmer</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link href="css/Style.css" rel="stylesheet">
</head>

<body>
<form method="post" action="./login">
            <div class="login-wrap">
                <div class="login-html">
                    <div class="login-form">
                        <div class="sign-up-htm">
                                <h3 class="fw-normal mb-5">Login Details</h3>
                            <div class="group">
                                <label class="form-label" for="username">Username</label>
                                <input type="text" id="username" name="username" class="input" />
                            </div>
                            <div class="group">
                                <label class="form-label" for="password">Password</label>
                                <input type="password" id="password" name="password" class="input" />
                            </div>
                            <div class="group">
                                <p class="loginhere">
                                    Don't have an account ? <a href="register.jsp" class="loginhere-link">Register here</a>
                            </div>
                            <div class="group">
                                <input type="submit" class="button" value="Sign In">
                            </div>
                            <%
                                String sessionMsg = (String) session.getAttribute("LOGIN_MSG");
                                if (sessionMsg != null)
                                    out.print("ERROR: " + sessionMsg + "<BR/>");

                            %>
                            <div class="hr"></div>
                    </div>
                </div>
            </div>
        </div>

</form>
</body>
</html>
