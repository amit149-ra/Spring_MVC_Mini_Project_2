<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>Unlock Account</title>
    <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    
</head>
<body>
    <div class="container">
        <h3>Unlock Account</h3>
        <form action="forgetPasswordBtn" method="post">
            <!-- Display Messages -->
            <div class="feedback">${SuccessMsg}</div>
            <div class="feedback">${FailedMsg}</div>

            <!-- Form Fields -->
            <table>
                <tr>
                    <td>Email:</td>
                    <td><input type="text" name="email"></td>
                </tr>
            </table>

            <!-- Submit Button -->
            <div style="text-align: center;">
                <input type="submit" value="Send Email" />
            </div>
        </form>
    </div>
</body>
</html>
