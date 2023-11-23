package br.com.rsds.crudspringlistcourses.enums.converters;

import java.util.stream.Stream;

import br.com.rsds.crudspringlistcourses.enums.Category;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, String> {

	@Override
	public String convertToDatabaseColumn(Category category) {
		return category.getValue();
	}

	@Override
	public Category convertToEntityAttribute(String value) {
		return Stream.of(Category.values()).filter(v -> v.getValue().equals(value)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}