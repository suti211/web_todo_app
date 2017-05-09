<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<script src="./js/todo.js"></script>
	<link rel="stylesheet" href="./stylesheet/todos.css">
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kábítószerezés</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
				<div class="todo-container">
					<div class="title">
						<h2>Your todo List</h2>
					</div>
					<div id="todo-storage">
<!-- 						shit goes here -->
					</div>
					<div class="footer">
						<span class="todo-line"><span class="todo-text">New todo:</span><input type="text" id="todo-input"><button class="addbtn">Add new item</button></span>
						
					</div>
				</div>
			</div>
			<div class="col-sm-1"></div>
		</div>
	</div>
</body>
</html>