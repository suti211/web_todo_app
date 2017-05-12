package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.ServletSlave;

@WebServlet("/ToDoServlet")
public class ToDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	ServletSlave slave;
	
    public ToDoServlet() {
        super();
        slave = new ServletSlave();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		slave.sendTodos(request, response);
		slave.sendActiveTodos(request, response);
		slave.sendInactiveTodos(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post request");
		slave.removeTodo(request, response);
		slave.addTodo(request, response);
		slave.changeState(request, response);
	}

}