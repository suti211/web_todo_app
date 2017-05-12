package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xalan.internal.xsltc.dom.ArrayNodeListIterator;

import model.State;
import model.Todo;
import model.User;

public class QueryHandler {
	
	SQLConnector connector;
	Connection connection;

	public QueryHandler() {
		connector = new SQLConnector();
		try{
			connection = connector.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Failed to get SQL Connection");
		}
	}

	public List<Todo> getUserTodos(User u){
		String sql = "SELECT * FROM todo WHERE ownerID = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Todo> todos = new ArrayList<>();
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, u.getId());
			rs = ps.executeQuery();
			
			while(rs.next()){
				todos.add(new Todo(rs.getInt("todoID"), rs.getString("name"), State.valueOf(rs.getString("state"))));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Query Error(getUserTodos): " + sql + ".");
		}
		
		return todos;
	}
	
	public void addTodosToUser(User u, String name){
		String sql = "INSERT INTO todo (ownerID, name, state) VALUES (?,?, \"NOT_DONE\")";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, u.getId());
			ps.setString(2, name);
			int rowsAffected = ps.executeUpdate();
			System.out.println("Insert succesful, affected rows:" + rowsAffected);
			
		} catch (SQLException e) {
			System.err.println("Query Error(AddTodosToUser): " + sql + ".");
		}
	}
	
	public List<User> getRegisteredUsers(){
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM users";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				users.add(new User(rs.getInt("id"), rs.getString("email"), rs.getString("pass")));
			}
			
		} catch (SQLException e) {
			System.err.println("Query Error: " + sql + ".");
		}
		
		return users;
	}
	
	public List<Todo> getAllTodo(){
		List<Todo> todos = new ArrayList<>();
		String sql = "SELECT * FROM todo";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				todos.add(new Todo(rs.getInt("todoID"), rs.getString("name"), State.valueOf(rs.getString("state"))));
			}
			
		} catch (SQLException e) {
			System.err.println("Query Error(getAllTodo): " + sql + ".");
		}
		
		return todos;
	}
	
	public void deleteTodo(Integer id){
		String sql = "DELETE FROM todo WHERE todoID = ?";
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Query Error (deleteTodo): " + sql + ".");
		}
	}
	
	public void changeTodoState(Integer id){
		String sql = "SELECT * FROM todo WHERE todoID = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rs.next();
			
			//System.out.println("RS:" + rs.getString("state"));
			if(State.valueOf(rs.getString("state")) == State.NOT_DONE){
				sql = "UPDATE todo SET state = 'DONE' WHERE todoID = ?";
				ps = connection.prepareStatement(sql);
				ps.setInt(1, id);
			} else {
				sql = "UPDATE todo SET state = NOT_DONE WHERE todoID = ?";
				ps = connection.prepareStatement(sql);
				ps.setInt(1, id);
			}
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Query Error: " + sql + ".");
		}
	}
	
	public List<Todo> getTodos(String status, User u){
		List<Todo> todos = new ArrayList<>();
		
		String sql = "SELECT * FROM todo WHERE state = ? AND ownerID = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, u.getId());
			rs = ps.executeQuery();
			
		while(rs.next()){
			System.out.println(rs.getString("state"));
			todos.add(new Todo(rs.getInt("todoID"), rs.getString("name"), State.valueOf(rs.getString("state"))));
		}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Query Error: " + sql + ".");
		}
		
		return todos;
	}
	
}
