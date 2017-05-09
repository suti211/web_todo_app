package model;

public class Todo {
	
	private Integer id;
	private String name;
	private State state;
	
	public Todo(Integer id, String name, State state) {
		super();
		this.id = id;
		this.name = name;
		this.state = state;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", name=" + name + ", state=" + state + "]\n";
	}
	
}
