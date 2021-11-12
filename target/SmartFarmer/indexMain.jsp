<%@ page import="com.smartfarmer.entities.Farmer" %><%--
  Created by IntelliJ IDEA.
  User: mugo367
  Date: 10/2/21
  Time: 10:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home | Smart Farmer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <style>
        .swal-overlay {
            background-color: rgba(194, 255, 218, 0.45);
        }
    </style>
</head>
<body>
    <nav id="comp-topnav" class="navbar navbar-dark bg-dark"></nav>

    <div class="container-fluid px-4" id="userSessionData" style="text-align:right;"></div>

    <div id="componentRender" class="container-fluid"></div>

    <script src="js/main.js"></script>
    <script src="js/app.js"></script>
    <script src="js/equipment.js"></script>
    <script src="js/activity.js"></script>
    <script src="js/employee.js"></script>
    <script src="js/field.js"></script>
    <script src="js/production.js"></script>
    <script src="js/transaction.js"></script>
    <script src="js/fieldTask.js"></script>

</body>
</html>
