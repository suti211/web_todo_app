package model;

import java.util.List;

public interface IDataFucker {
	List<Todo> getTodos(List<Todo> list);
	List<Todo> getActiveTodos(List<Todo> list);
	List<Todo> getInactiveTodos(List<Todo> list);
	Todo addTodo(String name, List<Todo> list);
	Todo getTodo(Integer id, List<Todo> list);
	void deleteTodo(Integer id, List<Todo> list);
	void changeTodoState(Todo todo);
}
