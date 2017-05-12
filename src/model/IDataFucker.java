package model;

import java.util.List;

public interface IDataFucker {
	List<Todo> getTodos(List<Todo> list);
	List<Todo> getActiveTodos(User u);
	List<Todo> getInactiveTodos(User u);
	void addTodo(String name, User u);
	Todo getTodo(Integer id, List<Todo> list);
	void deleteTodo(Integer id);
	void changeTodoState(Integer id);
}
