package br.com.rsds.crudspringlistcourses.dto.mapper;

import org.springframework.stereotype.Component;

import br.com.rsds.crudspringlistcourses.dto.CourseDTO;
import br.com.rsds.crudspringlistcourses.enums.Category;
import br.com.rsds.crudspringlistcourses.model.CoursesList;

// @Component o spring cria uma instacia da classe permitido que ela seja utilizada por outras classes incluido a classe de servico
@Component
public class CourseMapper {

	public CourseDTO toDo(CoursesList course) {
//		se o parametro course for null e chamar o  CourseDTO(), vai retornar NullPointerException, por isso o if()
		if (course == null) {
			return null;
		}

		return new CourseDTO(course.getId(), course.getName(), "Front-End");
	}

	public CoursesList toEntity(CourseDTO courseDTO) {

		if (courseDTO == null) {
			return null;
		}

		CoursesList course = new CoursesList();
//		se o id for diferente de null ele seta o id, caso contrario quem vai gerar o id e o banco de dados
		if (courseDTO.id() != null) {
			course.setId(courseDTO.id());
		}

		course.setName(courseDTO.name());
		course.setCategory(Category.FRONTEND);
		return course;
	}
}
