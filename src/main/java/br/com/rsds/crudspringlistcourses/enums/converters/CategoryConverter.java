package br.com.rsds.crudspringlistcourses.enums.converters;

import java.util.stream.Stream;

import br.com.rsds.crudspringlistcourses.enums.Category;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// agente pode ter mais de um valor em cada enumerador, 
// por isso e criada uma classe para que ela possa ser mapeada na model 
// permitindo que o spring saiba examente qual é o valor que deve ser persistido no banco de dados

// como agente quer salvar um atributo tipo string no banco dedados o Tipo Enum nao e uma coluna valida no banco de dados 
// e necessario implementar a interface AttributeConverter<Category, String> e adicionar os metodos da interface

// pede para o JPA aplicar essa conversao sempre que for necessario
@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, String> {

	@Override
//	esse metodo e utilizado quando for salvar um valor no banco de dados
//	ele recebe o valor do enumerador no atributo Category category
	public String convertToDatabaseColumn(Category category) {
		System.out.println("convertToDatabaseColumn: " + category + " passado no parametro do tipo Category");
//		retorna o valor do enumerdor como String para ser salvo no banco dedados
		return category.getValue();
	}

	@Override
//	quando for necessario ler um valor do banco de dados esse metodo vai ser utilizado
//	ele pega o valor tipo String do banco dedados e transforma em um valor do enumerador
	public Category convertToEntityAttribute(String value) {
		System.out.println("convertToEntityAttribute: " + value + " passado no parametro do tipo String");

//		Stream.of tranformar qualquer array de informacoes em um Stream
//		 Category.Values() retorna um array contendo todos os valores dos enumeradores
//		  .filter(v -> v.getValue().equals(value)) pega um valor do array e realiza uma comparacao booleana
//		.findFirst() retorna o primerio valor que atende a comparacao booleana do filter, pode ter mais de um valor que atenda, como queremos somente o primeiro valor, usamos o first()
//		.orElseThrow(IllegalArgumentException::new) caso o valor nao seja encontrado vai ser lancada uma excecao em tempo de runtime de execucao informando que esse valor e um argumento invalido

		return Stream.of(Category.values()).filter(v -> v.getValue().equals(value)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
