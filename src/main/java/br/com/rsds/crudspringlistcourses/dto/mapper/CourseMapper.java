package br.com.rsds.crudspringlistcourses.dto.mapper;

import org.springframework.stereotype.Component;

import br.com.rsds.crudspringlistcourses.dto.CourseDTO;
import br.com.rsds.crudspringlistcourses.enums.Category;
import br.com.rsds.crudspringlistcourses.model.Course;

// @Component o spring cria uma instacia da classe permitido que ela seja utilizada por outras classes incluido a classe de servico
@Component
public class CourseMapper {

	public CourseDTO toDto(Course course) {
		if (course == null) {
			return null;
		}

		return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(), course.getLessons());
	}

	public Course toEntity(CourseDTO courseDTO) {

		if (courseDTO == null) {
			return null;
		}

		Course course = new Course();
		if (courseDTO.id() != null) {
			course.setId(courseDTO.id());
		}

		course.setName(courseDTO.name());
		course.setCategory(convertCategoryValue(courseDTO.category()));
		return course;
	}

	public Category convertCategoryValue(String value) {

		if (value == null) {
			return null;
		}

		/*
		 * se existir uma categoria relacionada ao valor retorna a categoria que e do
		 * tipo Category, caso contrario retorna uma excecao
		 */
		return switch (value) {
		case "Front-End" -> Category.FRONTEND;
		case "Back-End" -> Category.BACKEND;
		default -> throw new IllegalArgumentException("Categoria inválida: " + value);
		};
	}
}
