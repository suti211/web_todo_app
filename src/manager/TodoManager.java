package manager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

import model.IDataFucker;
import model.State;
import model.Todo;

public class TodoManager implements IDataFucker {
	
	private List<Todo> todos = new ArrayList<>();
	public TodoManager(){
		Todo todo = new Todo(0, "Kakapisi", State.NOT_DONE);
		Todo todo1 = new Todo(1, "szarfos", State.NOT_DONE);
		Todo todo2 = new Todo(2, "hugyszeklet", State.NOT_DONE);
		Todo todo3 = new Todo(3, "v�gb�l", State.NOT_DONE);
		todos.add(todo);
		todos.add(todo1);
		todos.add(todo2);
		todos.add(todo3);
		
	}
	
	@Override
	public List<Todo> getTodos() {
		return todos;
	}

	@Override
	public Todo getTodo(Integer id) {
		return todos.stream().filter((todo) -> todo.getId() == id).findFirst().get();
	}

	@Override
	public void deleteTodo(Integer id) {
		todos.remove(getTodo(id));
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
	public Todo addTodo(String name) {
		Integer maxTodoIndex;
		if(todos.size() != 0){
			maxTodoIndex = todos.stream().max(Comparator.comparing(Todo::getId)).get().getId();	
		}else {
			maxTodoIndex = 0;
		}
		return new Todo(maxTodoIndex + 1, name, State.NOT_DONE);
	}

}
