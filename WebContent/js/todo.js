function loadTodos(){
	$.get("ToDoServlet", {get : "todos"}, function(response){
		
		response.forEach(function(element){			
//			$("#todo-storage").append("<div class=\"todo\" index=\"" + i.id + "\"><h3>" + i.name + "</h3><button class=\"todobtn\">Delete</button><button class=\"todobtn\">Done</button></div>");
			var todo = document.createElement("div");
			todo.classList.add("todo");
			var attribute = document.createAttribute("index");
			attribute.value = element.id;
			todo.setAttributeNode(attribute);
			
			var h3 = document.createElement("h3");
			var doneButton = document.createElement("button");
			var delButton = document.createElement("button");
			
			delButton.textContent = "Delete";
			delButton.classList.add("delbtn");
			doneButton.textContent = "Done";
			doneButton.classList.add("donebtn");
			
			todo.append(h3);
			
			h3.textContent = element.name;
			
			delButton.onclick = function(){
				$.post("ToDoServlet", {post : "remove", rID : element.id}, )
				todo.remove();
			};
			
			doneButton.onclick = function(){
				
			}
			
			todo.append(delButton);
			todo.append(doneButton);
			$("#todo-storage").append(todo);
		});
		
//		console.log(todoArray);
	});
}

$(document).ready(function(){
//	$("#ciganygomb").click(loadTodos);
	loadTodos();
});