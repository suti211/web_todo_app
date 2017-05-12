package manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.QueryHandler;
import model.User;

public class LoginManager {

	private List<User> registered;
	private QueryHandler query;

	public LoginManager() {
		query = new QueryHandler();
		registered = query.getRegisteredUsers();
		registered.stream().forEach(System.out::println);
	}

	public void validateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("pass");
		String jspResponse = "";
		boolean userMatch = false;

		if (email != null && password != null) {
			if (!(email.equals("")) && !(password.equals(""))) {
				for (User u : registered) {
					if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
						HttpSession session = request.getSession(true);
						session.setAttribute("user", u);
						response.sendRedirect("./index.jsp");
						userMatch = true;
						System.out.println("Successful login attempt: " + u.getEmail());
						break;
					}
				}
				if(!userMatch){
					// invalid credentials:
					jspResponse = "invalidSomething";
					System.out.println("Invalid login attempt: incorrect shit!");
				}
			} else {
				// password or email is an empty string
				jspResponse = "missingData";
				System.out.println("Invalid login attempt: missing data!");
			}
		} else {
			// password or email == null;
			System.out.println("Nullpointer fuckery");
		}
		
		if(!userMatch){
			request.setAttribute("status", jspResponse);
			RequestDispatcher rd = request.getRequestDispatcher("./login.jsp");
			rd.forward(request, response);			
		}
	}

}
