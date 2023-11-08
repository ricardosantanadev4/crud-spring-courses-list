package br.com.rsds.crudspringlistcourses.enums;

public enum Category {
	BACKEND("Back-End"), FRONTEND("Front-End");

	private String value;

	private Category(String value) {
		this.value = value;
		System.out.println("Category() Category.java: " + this.value);
	}

	public String getValue() {
		System.out.println("getValue() Category.java");
		return value;
	}

//	@Override
	public String toString() {
		System.out.println("toString() Category.java");
		return value;
	}

}