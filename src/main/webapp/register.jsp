<%--
  Created by IntelliJ IDEA.
  User: mugo367
  Date: 9/29/21
  Time: 5:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, shrink-to-fit=no, initial-scale=1">
    <title>Register | Smart Farmer</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link href="css/Style.css" rel="stylesheet">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

</head>

<body>
<form method="post" action="./register">
    <div class="card-group">
        <div class="card">
            <div class="card-body">
                <div class="register-wrap">
                    <div class="login-html">
                        <div class="login-form">
                            <div class="sign-up-htm">
                                <h3 class="sub-title">General Information</h3>
                                <div class="group">
                                    <label class="form-label" for="farmerName">Full Name</label>
                                    <input type="text" id="farmerName" name = "fullName" class="input" />
                                </div>
                                <div class="group">
                                    <label class="form-label" for="farmerUsername">Username</label>
                                    <input type="text" id="farmerUsername" name = "username" class="input" />
                                </div>
                                <div class="group">
                                    <label class="form-label" for="farmerNumber">Phone Number</label>
                                    <input type="text" id="farmerNumber" name= "phoneNumber" class="input" />
                                </div>
                                <div class="group">
                                    <label class="form-label" for="farmerEmail">Email Address</label>
                                    <input type="text" id="farmerEmail" name = "emailAddress" class="input" />
                                </div>
                                <div class="group">
                                    <label class="form-label" for="Password">Password</label>
                                    <input type="password" id="Password" name ="password" class="input" />
                                </div>
                                <div class="group">
                                    <label class="form-label" for="ConfirmPassword">Confirm Password</label>
                                    <input type="password" id="ConfirmPassword" class="input" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-body">
                <div class="register-wrap">
                    <div class="login-html">
                        <div class="login-form">
                            <div class="sign-up-htm">
                                <h3 class="sub-title">Farm Details</h3>
                                <div class="group">
                                    <label class="form-label" for="County">County</label>
                                    <input type="text" id="County" name="county" class="input" />
                                </div>
                                <div class="group">
                                    <label class="form-label" for="subCounty">Sub County</label>
                                    <input type="text" id="subCounty" name="subCounty" class="input" />
                                </div>
                                <div class="group">
                                    <label class="form-label" for="FarmName">Farm Name</label>
                                    <input type="text" id="FarmName" name="farmName" class="input" />
                                </div>
                                <div class="group">
                                    <label class="form-label" for="FarmSize">Farm Size</label>
                                    <input type="number" id="FarmSize" name="farmSize" class="input" />
                                </div>
                                <div class="group">
                                    <label class="form-label" for="farmAdditionInfo">Additional Information</label>
                                    <input type="text" id="farmAdditionInfo" name ="additionalInfo" class="input" />
                                </div>
                                <div class="group">
                                    <p class="loginhere">
                                        Have an account already? <a href="login.jsp" class="loginhere-link">Login here</a>
                                </div>
                                <div class="group">
                                    <input type="submit" class="button" value="Sign Up">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
</body>
</html>
