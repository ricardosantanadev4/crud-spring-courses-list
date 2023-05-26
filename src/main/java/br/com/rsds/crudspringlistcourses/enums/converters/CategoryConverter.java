package br.com.rsds.crudspringlistcourses.enums.converters;

import java.util.stream.Stream;

import br.com.rsds.crudspringlistcourses.enums.Category;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// @Converter(autoApply = true) pede para o JPA aplicar essa conversao sempre que for necessario
@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, String> {

	@Override
	public String convertToDatabaseColumn(Category category) {
		if (category == null) {
			return null;
		}

		return category.getValue();
	}

	@Override
	public Category convertToEntityAttribute(String value) {

		if (value == null) {
			return null;
		}

//		Stream.of() e uma classe utilitaria que transforma qualquer array de informacoes em uma stream
//		Category.values() e um array que contem os todos valores da classe Category
//		filter() consegue aplicar um filtro pegando o valor e realizando um comparacao booleana
//		.findFirst() retorna apenas o primeiro valor que for encontrado no .filter() porque o filter poder retornar um array de valores, mas estou interessado apenas no primeiro
//		.orElseThrow(IllegalArgumentException::new) caso não econtre o valor sera lancada um excecao em tempo de runtime informando que esse agurmento nao e um argumento valido
		return Stream.of(Category.values()).filter(c -> c.getValue().equals(value)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
