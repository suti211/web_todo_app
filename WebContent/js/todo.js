function loadTodos(array, filter){
	$.get("ToDoServlet", {get : filter}, function(response){
		
		if(response.length == 0){
			insertTodos(array);	
		}
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
					loadTodos(array, filter);
				});
			}
			
			todo.append(delButton);

			if(element.state != "DONE"){
				todo.append(doneButton);
			}

			buttonStateManager(filter);
			
			//$("#todo-storage").append(todo);
			array.push(todo);
			insertTodos(array);

		});
	});
}

//num1
function addTodos(array, filter){
	var todoName = $("#todo-input").val();
	$.post("ToDoServlet", {post : "add" , name : todoName}, function(){
		$("#todo-input").val("");
		array = [];
		loadTodos(array, filter);
	});
}

function insertTodos(niggarray){
	$("#todo-storage").empty();
	var test = document.querySelector("#todo-storage");
	niggarray.forEach(function(element){
		test.appendChild(element);
	});
}

function buttonStateManager(filter){
	if(filter == "todos"){
			document.querySelector("#all").classList.add("active");
			document.querySelector("#active").classList.remove("active");
			document.querySelector("#done").classList.remove("active");
			} else if (filter == "activeTodos"){
			document.querySelector("#all").classList.remove("active");
			document.querySelector("#active").classList.add("active");
			document.querySelector("#done").classList.remove("active");
			} else if (filter == "inactiveTodos"){
			document.querySelector("#all").classList.remove("active");
			document.querySelector("#active").classList.remove("active");
			document.querySelector("#done").classList.add("active");
	}
}

$(document).ready(function(){
	var criteria = "todos"
	var todoArray = [];
	loadTodos(todoArray, criteria);

	$(".addbtn").click(function(){addTodos(todoArray, criteria);});

	//detect enter on input field
	$("#todo-input").on('keyup', function (e) {
		if (e.keyCode == 13) {
			addTodos(todoArray, criteria);
		}
	});

	$("#all").click(function(){
		criteria = "todos"
		todoArray = [];
		loadTodos(todoArray, criteria);
	});

	$("#done").click(function(){
		criteria = "inactiveTodos"
		todoArray = [];
		loadTodos(todoArray, criteria);
	});

	$("#active").click(function(){
		criteria = "activeTodos"
		todoArray = [];
		loadTodos(todoArray, criteria);
	});

	//console.log(todoArray);
	//insertTodos(todoArray);
	//$.when(loadTodos(todoArray)).then(insertTodos(todoArray));
	
});