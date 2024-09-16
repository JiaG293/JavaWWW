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
<%@ page import="vn.edu.iuh.fit.entities.Account" %>
<%@ page import="vn.edu.iuh.fit.entities.Role" %>
<%@ page import="java.util.List" %>
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
    <title>Dashboard</title>
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
        <%= account.getRole().getRoleName() + ", " + account.getAccount().getFullName()%>
    </h1>

    <%--THONG TIN ACCOUNT--%>
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

    <%--DANH SACH ACCOUNT CUA ROLE--%>

    <div class="card mt-3">
        <div class="card-header">
            List Account of Role
        </div>
        <div class="mt-3 d-flex justify-content-center">
            <form action="manage" method="get" class="mx-2">
                <input type="hidden" name="action" value="getlistAccountOfRole_admin">
                <button type="submit" class="btn btn-outline-secondary">Admin</button>
            </form>
            <form  action="manage"  method="get" class="mx-2">
                <input type="hidden" name="action" value="getlistAccountOfRole_user">
                <button type="submit" class="btn btn-outline-secondary">User</button>
            </form>
        </div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Account ID</th>
                <th scope="col">Full Name</th>
                <th scope="col">Email</th>
                <th scope="col">Phone</th>
                <th scope="col">Is Grant</th>
                <th scope="col">Status</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<AccountGrantDTO> listAccountOfRole = (List<AccountGrantDTO>) request.getAttribute("listAccountOfRole");
                if (listAccountOfRole != null) {
                    int index = 1;
                    for (AccountGrantDTO acc : listAccountOfRole) {
            %>
            <tr>
                <th scope="col"><%= index %></th>
                <td><%= acc.getAccount().getAccountId() %></td>
                <td><%= acc.getAccount().getFullName() %></td>
                <td><%= acc.getAccount().getEmail() %></td>
                <td><%= acc.getAccount().getPhone() %></td>
                <td><%= acc.getGrant() %></td>
                <td><%= acc.getAccount().getStatus() %></td>
            </tr>
            <%
                    index++;
                    }
            } else {
            %>
            <tr>
                <td colspan="3">No accounts found</td>
            </tr>
            <% } %>
            <%--<tr>
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
            </tr>
            <tr>
                <th scope="row">2</th>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>@fat</td>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
            </tr>
            <tr>
                <th scope="row">3</th>
                <td>Larry</td>
                <td>the Bird</td>
                <td>@twitter</td>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
            </tr>--%>

            </tbody>
        </table>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.11.6/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"></script>
</body>
</html>


