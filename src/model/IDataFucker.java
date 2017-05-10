package model;

import java.util.List;

public interface IDataFucker {
	List<Todo> getTodos();
	List<Todo> getActiveTodos();
	List<Todo> getInactiveTodos();
	Todo addTodo(String name);
	Todo getTodo(Integer id);
	void deleteTodo(Integer id);
	void changeTodoState(Todo todo);
}
