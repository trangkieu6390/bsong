package model.bean;

public class Category {
	private int id;
	private String name;
	private int count;
	private int index_page;
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Category(int id, String name, int count) {
		super();
		this.id = id;
		this.name = name;
		this.count = count;
	}
	public Category(int count) {
		super();
		this.count = count;
	}
	public int getIndex_page() {
		return index_page;
	}
	public void setIndex_page(int index_page) {
		this.index_page = index_page;
	}
	public Category(int count, int index_page) {
		super();
		this.count = count;
		this.index_page = index_page;
	}
	

}
