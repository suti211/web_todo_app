function loadTodos(array){
	$.get("ToDoServlet", {get : "todos"}, function(response){
		
		//console.log(response);
		response.forEach(function(element){			
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
			
			if(element.state == "DONE"){
				todo.classList.add("todo-done");
			}

			todo.append(h3);
			
			h3.textContent = element.name;
			
			delButton.onclick = function(){
				$.post("ToDoServlet", {post : "remove", rID : element.id}, function(){
					todo.remove();
				});
			};
			
			doneButton.onclick = function(){
				$.post("ToDoServlet", { post : "changeState", index : element.id}, function(){
					array = [];
					loadTodos(array);
				});
			}
			
			todo.append(delButton);

			if(element.state != "DONE"){
				todo.append(doneButton);
			}
			
			//$("#todo-storage").append(todo);
			array.push(todo);
			insertTodos(array);
		});
	});
}

//num1
function addTodos(array){
	var todoName = $("#todo-input").val();
	$.post("ToDoServlet", {post : "add" , name : todoName}, function(){
		$("#todo-input").val("");
		array = [];
		loadTodos(array);
	});
}

function insertTodos(niggarray){
	$("#todo-storage").empty();
	var test = document.querySelector("#todo-storage");
	niggarray.forEach(function(element){
		test.appendChild(element);
	});
}

$(document).ready(function(){
	var todoArray = [];
	loadTodos(todoArray);
	$(".addbtn").click(addTodos);

	//detect enter on input field
	$("#todo-input").on('keyup', function (e) {
		if (e.keyCode == 13) {
			addTodos(todoArray);
		}
	});

	//console.log(todoArray);
	//insertTodos(todoArray);
	//$.when(loadTodos(todoArray)).then(insertTodos(todoArray));
	
});