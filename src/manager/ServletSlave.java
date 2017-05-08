package manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Todo;

public class ServletSlave {
	private TodoManager tm;
	Gson gson;
	
	public ServletSlave(){
		tm = new TodoManager();
		gson =  new Gson();
	}
	
	public List<Todo> getTodos(){
		return tm.getTodos();
	}
	
	public void sendTodos(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		if(request.getParameter("get") != null && request.getParameter("get").equals("todos") ){
			String json = gson.toJson(getTodos());
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print(json);
			System.out.println("JsonObject Sent!");
			out.flush();
		}
	}
}
