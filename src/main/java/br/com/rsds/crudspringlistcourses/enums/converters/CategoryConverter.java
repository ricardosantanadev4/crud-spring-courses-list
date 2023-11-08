package br.com.rsds.crudspringlistcourses.enums.converters;

import java.util.stream.Stream;

import br.com.rsds.crudspringlistcourses.enums.Category;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, String> {

	@Override
	public String convertToDatabaseColumn(Category category) {
		System.out.println("convertToDatabaseColumn: " + category + " passado no parametro do tipo Category");
		return category.getValue();
	}

	@Override
	public Category convertToEntityAttribute(String value) {
		System.out.println("convertToEntityAttribute: " + value + " passado no parametro do tipo String");

		return Stream.of(Category.values()).filter(v -> v.getValue().equals(value)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}