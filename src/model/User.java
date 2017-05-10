package model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private Integer id;
	private String email;
	private String password;
	private List<Todo> todos = new ArrayList<>();
	
	public User(Integer id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + "]";
	}

	public Integer getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public List<Todo> getTodos() {
		return todos;
	}
	
}
