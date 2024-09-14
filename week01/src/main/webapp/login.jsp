<%--
  Created by IntelliJ IDEA.
  User: JiaG
  Date: 13/09/2024
  Time: 3:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        form {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            box-sizing: border-box;
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"], input[type="reset"], input[type="button"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 15px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            margin-right: 10px;
        }

        input[type="reset"], input[type="button"] {
            background-color: #6c757d;
        }

        input[type="submit"]:hover, input[type="reset"]:hover, input[type="button"]:hover {
            opacity: 0.8;
        }

        .show-password {
            display: flex;
            align-items: center;
        }


    </style>
</head>
<body>

<form method="post" action="login">
    <label for="username">User Name:</label>
    <input type="text" id="username" name="username" placeholder="username or @email.com"
           oninput="validateInput('username', 'username')"/>
    <span id=err-username"></span>
    <br/>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" placeholder="minimum 6 characters"
           oninput="validateInput('password', 'password')"
    />
    <span id=err-password></span>
    <a style="color: green; display: flex; justify-content: flex-end" href="signin.html">Sign In</a>
    <br/>
    <div class="show-password">
        <input type="checkbox" id="showPassword">
        <label for="showPassword" style="margin-top: 10px; font-size: 12px; font-weight: lighter">Show Password</label>
    </div>
    <br/>
    <input type="submit" value="Login"/>
    <input type="reset" value="Clear"/>
    <input type="button" value="Test" onclick="testFill()"/>


</form>

<script>
    const testFill = () => {
        document.getElementById('username').value = 'teo';
        document.getElementById('password').value = '123';
    }

    document.getElementById('showPassword').addEventListener('change', function () {
        let passwordField = document.getElementById('password');
        this.checked ? passwordField.type = 'text' : passwordField.type = 'password';
    });

    const validateInput = (elementId, type) => {
        let inputElement = document.getElementById(elementId);
        let errorElement = document.getElementById(`err-${elementId}`);
        let input = inputElement.value;

        let isValid = true;
        let errorMsg = '';

        switch (type) {
            case 'username':
                if (input.length < 6) {
                    errorMsg = 'Must be at least 6 characters long.';
                    isValid = false;
                }
                break;
            case 'email':
                const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                if (!emailPattern.test(input)) {
                    errorMsg = 'Please enter a valid email address.';
                    isValid = false;
                }
                break;
            case 'password':
                if (input.length < 6) {
                    errorMsg = 'Must be at least 6 characters long.';
                    isValid = false;
                }
                break;
            default:
                break;
        }

        if (isValid) {
            errorElement.textContent = 'OK';
            errorElement.style.color = 'green';
            errorElement.ariaChecked = 'true';
        } else {
            errorElement.textContent = errorMsg;
            errorElement.style.color = 'red';
            errorElement.ariaChecked = 'false';
        }
    }


</script>
</body>
</html>
