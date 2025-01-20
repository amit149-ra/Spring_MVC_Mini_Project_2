<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>Unlock Account</title>
    <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        h3 {
            text-align: center;
            color: #333;
        }

        table {
            width: 100%;
        }

        td {
            padding: 8px;
        }

        td:first-child {
            text-align: right;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: #007BFF;
            color: white;
            border: none;
            cursor: pointer;
            margin-top: 10px;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .feedback {
            font-size: 12px;
            margin-top: 5px;
        }

        .weak {
            color: red;
        }

        .medium {
            color: orange;
        }

        .strong {
            color: green;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function() {
            // Password strength checker
            $("#newPassword").on("input", function() {
                const password = $(this).val();
                let strength = "weak";
                if (password.length > 8 && /[A-Z]/.test(password) && /[0-9]/.test(password)) {
                    strength = "strong";
                } else if (password.length > 6) {
                    strength = "medium";
                }
                const feedbackClass = strength === "strong" ? "strong" : strength === "medium" ? "medium" : "weak";
                $("#passwordStrength").attr("class", `feedback ${feedbackClass}`).text(`Password strength: ${strength}`);
            });
        });
    </script>
</head>
<body>
    <div class="container">
        <h3>Unlock Account</h3>
        <form:form action="handleUnlockAccount?email=${userAcc.email}" method="post" modelAttribute="userAcc">
            <!-- Display Messages -->
            <div class="feedback">${SuccessMsg}</div>
            <div class="feedback">${FailedMsg}</div>

            <!-- Form Fields -->
            <table>
                <tr>
                    <td>Email:</td>
                    <td><p>${userAcc.email}</p></td>
                </tr>
                <tr>
                    <td>Temporary Password:</td>
                    <td><form:password path="tempPassword" /></td>
                </tr>
                <tr>
                    <td>New Password:</td>
                    <td>
                        <form:password path="newPassword" id="newPassword" />
                        <div id="passwordStrength" class="feedback"></div>
                    </td>
                </tr>
                <tr>
                    <td>Confirm Password:</td>
                    <td><form:password path="confirmPassword" /></td>
                </tr>
            </table>

            <!-- Submit Button -->
            <div style="text-align: center;">
                <input type="submit" value="Unlock" />
            </div>
        </form:form>
    </div>
</body>
</html>
