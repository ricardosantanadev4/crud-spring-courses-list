package br.com.rsds.crudspringlistcourses.dto.mapper;

import org.springframework.stereotype.Component;

import br.com.rsds.crudspringlistcourses.dto.CourseDTO;
import br.com.rsds.crudspringlistcourses.enums.Category;
import br.com.rsds.crudspringlistcourses.model.Courses;

// @Component o spring cria uma instacia da classe permitido que ela seja utilizada por outras classes incluido a classe de servico
@Component
public class CourseMapper {

	public CourseDTO toDto(Courses course) {
//		se o parametro course for null e chamar o  CourseDTO(), vai retornar NullPointerException, por isso o if()
		if (course == null) {
			return null;
		}

		return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue());
	}

	public Courses toEntity(CourseDTO courseDTO) {

		if (courseDTO == null) {
			return null;
		}

		Courses course = new Courses();
//		se o id for diferente de null ele seta o id, caso contrario quem vai gerar o id e o banco de dados
		if (courseDTO.id() != null) {
			course.setId(courseDTO.id());
		}

		course.setName(courseDTO.name());
//		courseDTO.category() e do tipo string nas validacoes do CourseDTO, e course.setCategory() e do Category, por isso o uso do metodo convertCategoryValue(String value)
		course.setCategory(convertCategoryValue(courseDTO.category()));
		return course;
	}

//	recebe o valor um valor do tipo String e retorna uma categria do Tipo Category
	public Category convertCategoryValue(String value) {

		if (value == null) {
			return null;
		}

//		se existir uma categoria relacionada ao valor retorna a categoria que e do tipo Category, caso contrario retorna uma excecao
		return switch (value) {
		case "Front-End" -> Category.FRONTEND;
		case "Back-End" -> Category.BACKEND;
		default -> throw new IllegalArgumentException("Categoria inválida: " + value);
		};
	}
}
