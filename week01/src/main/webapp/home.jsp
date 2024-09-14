<%--
  Created by IntelliJ IDEA.
  User: JiaG
  Date: 13/09/2024
  Time: 12:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%--Kiem tra thong tin sesstion co ton tai hay khong--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="vn.edu.iuh.fit.entities.DTO.AccountGrantDTO" %>
<%
    AccountGrantDTO account = (AccountGrantDTO) session.getAttribute("accountData");
    if (account == null) {
        response.sendRedirect("/login.jsp");
        return;
    }
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            padding-top: 50px;
        }

        .container {
            max-width: 800px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top">
    <div class="container">
        <a class="navbar-brand" href="#">WEEK01_NguyenVanGiau_20053331</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="logout">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <h1 class="mt-5">Welcome
        <%= account.getRole().getRoleName() + ", " %>
        <%= (account != null) ? account.getAccount().getFullName() : "Guest" %>
    </h1>
    <div class="card mt-3">
        <div class="card-header">
            Account Details
        </div>
        <div class="card-body">
            <% if (account != null) { %>
            <h5 class="card-title">Account ID: <%= account.getAccount().getAccountId() %>
            </h5>
            <p class="card-text">Full Name: <%= account.getAccount().getFullName() %>
            </p>
            <p class="card-text">Email: <%= account.getAccount().getEmail() %>
            </p>
            <p class="card-text">Phone: <%= account.getAccount().getPhone() %>
            </p>
            <p class="card-text">Role: <%= account.getRole().getRoleName() %>
            </p>
            <% } else { %>
            <p class="card-text">No account data available.</p>
            <% } %>
        </div>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.11.6/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"></script>
</body>
</html>


