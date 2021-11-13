<%--
  Created by IntelliJ IDEA.
  User: mugo367
  Date: 9/29/21
  Time: 5:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, shrink-to-fit=no, initial-scale=1">
        <title>Register | Smart Farmer</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <link rel="stylesheet" href="css/Style.css">
    </head>

    <body>
        <form method="post" action="./register" class="needs-validation">
            <div class="container-fluid">
                <p><h3 style=" text-align: center"> WELCOME TO SMARTFARMER</h3></p>
                <div class="card-group">
                    <div class="col-sm-2" ></div>
                    <div class="col-sm-4" >
                        <div class="card" style="height: 600px;">
                            <div class="card-body">
                                <h4 class="sub-title">General Information</h4>
                                <div class="mb-3">
                                    <label class="form-label" for="farmerName">Full Name</label>
                                    <input type="text" id="farmerName" name = "fullName" class="form-control" required/>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label" for="farmerUsername">Username</label>
                                    <input type="text" id="farmerUsername" name = "username" class="form-control" required/>
                                    <p style="color: red;">ERROR!!:  <% String sessionMsg = (String) session.getAttribute("message"); if (sessionMsg != null) out.print(sessionMsg);%> </p>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label" for="farmerNumber">Phone Number</label>
                                    <input type="text" id="farmerNumber" name= "phoneNumber" class="form-control" required/>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label" for="farmerEmail">Email Address</label>
                                    <input type="email" id="farmerEmail" name = "emailAddress" class="form-control" required/>
                                    <p style="color: red;">ERROR!!:  <%if (sessionMsg != null) out.print(sessionMsg);%> </p>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label" for="Password">Password</label>
                                    <input type="password" id="Password" name ="password"  name="psw" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" class="form-control" required/>
                                </div>
                                <div id="message" class="form-text">
                                    <h5>Password must contain the following:</h5>
                                    <p id="letter" class="invalid">A <b>lowercase</b> letter</p>
                                    <p id="capital" class="invalid">A <b>capital (uppercase)</b> letter</p>
                                    <p id="number" class="invalid">A <b>number</b></p>
                                    <p id="length" class="invalid">Minimum <b>8 characters</b></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4" >
                        <div class="card" style="height: 600px;">
                            <div class="card-body">
                                <h4 class="sub-title">Farm Details</h4>
                                <div class="mb-3">
                                    <label class="form-label" for="County">County</label>
                                    <input type="text" id="County" name="county" class="form-control" required/>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label" for="subCounty">Sub County</label>
                                    <input type="text" id="subCounty" name="subCounty" class="form-control" required/>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label" for="FarmName">Farm Name</label>
                                    <input type="text" id="FarmName" name="farmName" class="form-control" required/>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label" for="FarmSize">Farm Size</label>
                                    <input type="number" min="1" id="FarmSize" name="farmSize" class="form-control" required/>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label" for="farmAdditionInfo">Additional Information</label>
                                    <input type="text" id="farmAdditionInfo" name ="additionalInfo" class="form-control" required/>
                                </div>
                                <div class = "d-grid gap-2 d-md-flex justify-content-md-end">
                                    <p>Have an account already? <a href="./login.jsp" class="btn btn-link" role="button" aria-disabled="true">Login</a></p>
                                </div>
                                <div class="d-grid gap-2 col-6 mx-auto">
                                    <button id="submit" class="btn btn-success" type="submit">Register</button>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-2"></div>
                </div>
            </div>
        </form>


        <script src="js/register.js"></script>
    </body>
</html>
