package br.com.rsds.crudspringlistcourses.enums;

public enum Category {
//	passa o valor de cada enum para o contrutor assim que o programa e executado
	BACKEND("Back-End"), FRONTEND("Front-End");

// e o valor que vai ser salvo no banco de dados
	private String value;

//	como o contrutor nao vai ser instaciado em outra classe ele vai ser private
// esse contrutor revebe o valor de ("Back-End") ou FRONTEND("Front-End") e passa valor recebido para a variavel value da Classe
	private Category(String value) {
		this.value = value;
		System.out.println("Category() Category.java: " + this.value);
	}

//	esse meto do vai se publico para que outra classe de outro pacote possa pegar o valor do enumerador que vai ser salvo no banco de dados
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
