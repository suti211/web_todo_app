package manager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

import model.IDataFucker;
import model.State;
import model.Todo;

public class TodoManager implements IDataFucker {
	
	public TodoManager(){
	
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
	public void deleteTodo(Integer id, List<Todo> list) {
		list.remove(getTodo(id, list));
	}

	@Override
	public void changeTodoState(Todo todo) {
		if(todo.getState() == State.DONE){
			todo.setState(State.NOT_DONE);
		} else {
			todo.setState(State.DONE);
		}
		
	}

	@Override
	public Todo addTodo(String name, List<Todo> list) {
		Integer maxTodoIndex;
		if(list.size() != 0){
			maxTodoIndex = list.stream().max(Comparator.comparing(Todo::getId)).get().getId();	
		}else {
			maxTodoIndex = 0;
		}
		return new Todo(maxTodoIndex + 1, name, State.NOT_DONE);
	}

	@Override
	public List<Todo> getActiveTodos(List<Todo> list) {
		return list.stream().filter(todo -> todo.getState() == State.NOT_DONE).collect(Collectors.toList());
	}

	@Override
	public List<Todo> getInactiveTodos(List<Todo> list) {
		return list.stream().filter(todo -> todo.getState() == State.DONE).collect(Collectors.toList());
	}
	
	

}
