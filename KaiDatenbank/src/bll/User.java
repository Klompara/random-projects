package bll;

public class User {

	private int id;
	private String name;
	private int alter;

	public User(int id, String name, int alter) {
		this.id = id;
		this.name = name;
		this.alter = alter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAlter() {
		return alter;
	}

	public void setAlter(int alter) {
		this.alter = alter;
	}

}
