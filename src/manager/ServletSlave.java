package manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Todo;
import model.User;

public class ServletSlave {
	private TodoManager tm;
	Gson gson;

	public ServletSlave() {
		tm = new TodoManager();
		gson = new Gson();
	}

//	public List<Todo> getTodos() {
//		return tm.getTodos();
//	}
//	
	public List<Todo> getTodosFromSession(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession(false);
		
		if(session != null){
			User u = (User) session.getAttribute("user");
			if(u != null){
				return u.getTodos();
			} else {
				System.out.println("failed to get user (user == null)");
			}
		} else{
			System.out.println("The session was null, failed to get user Todos");
		}
		
		return null;
	}

	public void sendTodos(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		if (request.getParameter("get") != null && request.getParameter("get").equals("todos")) {
			String json = gson.toJson(getTodosFromSession(request, response));
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print(json);
			System.out.println("JsonObject Sent!");
			out.flush();
		}
	}

	public void removeTodo(HttpServletRequest request, HttpServletResponse response) {
		String msg = request.getParameter("post");

		if (msg != null && msg.equals("remove")) {
			Integer removalId = Integer.valueOf(request.getParameter("rID"));
			tm.deleteTodo(removalId, getTodosFromSession(request, response));
			System.out.println("todo deleted id: " + removalId);
		}

	}

	public void addTodo(HttpServletRequest request, HttpServletResponse response) {
		String msg = request.getParameter("post");
		String todoName = request.getParameter("name");

		if (msg != null) {
			if (msg.equals("add")) {
				if (!(todoName.equals(""))) {
					List<Todo> userTodos = getTodosFromSession(request, response);
					userTodos.add(tm.addTodo(todoName, userTodos));
					Stream.of(userTodos).forEach(System.out::println);
				} else {
					System.out.println("new todo name is empty, shit happens");
				}
			}
		} else {
			System.out.println("msg = null you fuckface");
		}
	}

	public void changeState(HttpServletRequest request, HttpServletResponse response) {
		String msg = request.getParameter("post");

		if (msg != null ) {
			if (msg.equals("changeState")) {
				Integer changeID = Integer.parseInt(request.getParameter("index"));
				tm.changeTodoState(tm.getTodo(changeID, getTodosFromSession(request, response)));
			}
		} else {
			System.out.println("msg or changeId is null at statechange");
		}
	}
	
	public void sendActiveTodos(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if (request.getParameter("get") != null && request.getParameter("get").equals("activeTodos")) {
			String json = gson.toJson(tm.getActiveTodos(getTodosFromSession(request, response)));
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print(json);
			System.out.println("JsonObject Sent(activetodos)!");
			out.flush();
		}
	}
	
	public void sendInactiveTodos(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if (request.getParameter("get") != null && request.getParameter("get").equals("inactiveTodos")) {
			String json = gson.toJson(tm.getInactiveTodos(getTodosFromSession(request, response)));
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print(json);
			System.out.println("JsonObject Sent(inactivetodos)!");
			out.flush();
		}
	}
}
