<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Sign In</h1>
	<font color='red'>${msg}</font>
	<form action="signin" method="post">
		<table>
			<tr>
				<td>Email</td>
				<td><input type="text" name="email" /></td>
			</tr>
			<tr>
				<td>password</td>
				<td><input type="password" name="pwd" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Sign In" /></td>
			</tr>
			<tr>
				<td><a href="forgetPassword">Forget Password</a></td>
				<td><a href="regForm">Sign Up</a></td>
			</tr>
		</table>
	</form>

</body>
</html>