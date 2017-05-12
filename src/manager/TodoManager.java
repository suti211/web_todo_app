package manager;

import java.util.List;
import java.util.stream.Collectors;

import db.QueryHandler;
import model.IDataFucker;
import model.State;
import model.Todo;
import model.User;

public class TodoManager implements IDataFucker {
	
	QueryHandler query;
	
	public TodoManager(){
		query = new QueryHandler();
	}
	
	@Override
	public List<Todo> getTodos(List<Todo> list) {
		return list;
	}

	@Override
	public Todo getTodo(Integer id, List<Todo> list) {
		return list.stream().filter((todo) -> todo.getId() == id).findFirst().get();
	}

	@Override
	public void deleteTodo(Integer id) {
		query.deleteTodo(id);
	}

	@Override
	public void changeTodoState(Integer id) {
		query.changeTodoState(id);
	}
	
	@Override
	public void addTodo(String name, User u) {
		query.addTodosToUser(u, name);
	}

	@Override
	public List<Todo> getActiveTodos(User u) {
		//return list.stream().filter(todo -> todo.getState() == State.NOT_DONE).collect(Collectors.toList());
		return query.getTodos(State.NOT_DONE.toString(), u);
	}

	@Override
	public List<Todo> getInactiveTodos(User u) {
		//return list.stream().filter(todo -> todo.getState() == State.DONE).collect(Collectors.toList());
		return query.getTodos(State.DONE.toString(), u);
	}
	
	

}
