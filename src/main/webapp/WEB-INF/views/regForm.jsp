<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>User Registration</title>
    <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
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
            border-collapse: collapse;
        }

        td {
            padding: 10px;
            vertical-align: middle;
        }

        td:first-child {
            text-align: right;
            font-weight: bold;
            color: #555;
        }

        td:last-child {
            text-align: left;
        }

        input, select {
            width: 100%;
            padding: 8px;
            font-size: 14px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        input[type="submit"], input[type="reset"] {
            width: auto;
            padding: 10px 20px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover, input[type="reset"]:hover {
            background-color: #0056b3;
        }

        .error-message {
            color: red;
            font-size: 12px;
        }

        .success-message {
            color: green;
            font-size: 14px;
            text-align: center;
        }

        .button-container {
            text-align: center;
            margin-top: 20px;
        }
    </style>

    <script type="text/javascript">
        $(document).ready(function() {
            // Email validation
            $("#userEmail").on("blur", function() {
                $.ajax({
                    type: "GET",
                    url: "uniqueMailCheck?email=" + $("#userEmail").val(),
                    success: function(data) {
                        console.log(data);
                        if (data === "DUPLICATE") {
                            $("#duplicateEmail").text("Duplicate Email");
                            $(':input[type="submit"]').prop('disabled', true);
                        } else {
                            $("#duplicateEmail").text("");
                            $(':input[type="submit"]').prop('disabled', false);
                        }
                    }
                });
            });

            // Country dropdown change
            $("#country_dropdown").on('change', function() {
                $.ajax({
                    type: "GET",
                    url: "countryChange?countryId=" + $("#country_dropdown").val(),
                    success: function(data) {
                        console.log(data);
                        $("#state_dropdown").empty().append('<option value="" selected>Select State</option>');
                        $.each(data, function(key, value) {
                            $("#state_dropdown").append($('<option>').text(value).attr('value', key));
                        });
                    }
                });
            });

            // State dropdown change
            $("#state_dropdown").on('change', function() {
                $.ajax({
                    type: "GET",
                    url: "stateChange?stateId=" + $("#state_dropdown").val(),
                    success: function(data) {
                        console.log(data);
                        $("#city_dropdown").empty().append('<option value="" selected>Select City</option>');
                        $.each(data, function(key, value) {
                            $("#city_dropdown").append($('<option>').text(value).attr('value', key));
                        });
                    }
                });
            });
        });
    </script>
</head>
<body>
    <div class="container">
        <h3>Register Here</h3>
        <form:form action="registerUserbtn" method="post" modelAttribute="userAcc">
            <!-- Success and Failure Messages -->
            <div class="success-message">${SuccessMsg}</div>
            <div class="error-message">${FailedMsg}</div>

            <!-- Registration Form -->
            <table>
                <tr>
                    <td>First Name:</td>
                    <td><form:input path="firstName" /></td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td><form:input path="lastName" /></td>
                </tr>
                <tr>
                    <td>Gender:</td>
                    <td>
                        <form:radiobutton path="gender" value="m" /> Male
                        <form:radiobutton path="gender" value="f" /> Female
                    </td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td>
                        <form:input path="userEmail" id="userEmail" />
                        <span id="duplicateEmail" class="error-message"></span>
                    </td>
                </tr>
                <tr>
                    <td>Phone Number:</td>
                    <td><form:input path="contactNumber" /></td>
                </tr>
                <tr>
                    <td>DOB:</td>
                    <td><form:input path="userDOB" type="date" /></td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td>
                        <form:select path="countryId" id="country_dropdown">
                            <form:option value="">-- Select Country --</form:option>
                            <form:options items="${countries}" />
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td>
                        <form:select path="stateId" id="state_dropdown">
                            <form:option value="">-- Select State --</form:option>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td>
                        <form:select path="cityId" id="city_dropdown">
                            <form:option value="">-- Select City --</form:option>
                        </form:select>
                    </td>
                </tr>
            </table>

            <!-- Buttons -->
            <div class="button-container">
                <input type="reset" value="Reset" />
                <input type="submit" value="Register" />
            </div>
        </form:form>
    </div>
</body>
</html>
