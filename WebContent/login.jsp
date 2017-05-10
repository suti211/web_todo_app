<%@ page language="java" session="false"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>
<!-- <script src="./js/todo.js"></script> -->
<link rel="stylesheet" href="./stylesheet/todos.css">
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
				<div class="todo-container">
					<div class="title">
						<h2>Login</h2>
					</div>
					<div id="todo-storage" style="margin: auto">
						<!--shit goes here -->
						<div class="login">
							<form action="./LoginServlet" method="post">
								Email:<br>
								<input type="email" id="email" name="email"><br>
								Password:<br>
								<input type="password" id="pass" name="pass"> <br>
								<input type="submit" value="Login" id="loginsubmit">
							</form>
						</div>
					</div>
					<div class="footer">
						<div>
							<%
								String stat = (String) request.getAttribute("status");
								String msg = "";
								if (stat != null) {
									if (stat.equals("invalidSomething")) {
										msg = "<div class='status'>Incorrect credentials!</div>";
									} else if (stat.equals("missingData")){
										msg = "<div class='status'>Missing password!</div>";
									}
								}
								
								out.println(msg);
								
							%>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-1"></div>
		</div>
	</div>
</body>
</html>