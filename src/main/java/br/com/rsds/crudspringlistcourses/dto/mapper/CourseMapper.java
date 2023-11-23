package br.com.rsds.crudspringlistcourses.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.rsds.crudspringlistcourses.dto.CourseDTO;
import br.com.rsds.crudspringlistcourses.dto.LessonDTO;
import br.com.rsds.crudspringlistcourses.enums.Category;
import br.com.rsds.crudspringlistcourses.model.Course;
import br.com.rsds.crudspringlistcourses.model.Lesson;

// @Component o spring cria uma instacia da classe permitido que ela seja utilizada por outras classes incluido a classe de servico
@Component
public class CourseMapper {

	public CourseDTO toDto(Course course) {
		if (course == null) {
			return null;
		}

		List<LessonDTO> lessons = course.getLessons().stream()
				.map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeURL()))
				.collect(Collectors.toList());

		return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(), lessons);

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

		List<Lesson> lessons = courseDTO.lessons().stream().map(lessonsDTO -> {
			var lesson = new Lesson();
			lesson.setId(lessonsDTO.id());
			lesson.setName(lessonsDTO.name());
			lesson.setYoutubeURL(lessonsDTO.youtubeURL());
			lesson.setCourse(course);
			return lesson;
		}).collect(Collectors.toList());

		course.setLessons(lessons);
		return course;
	}

	public Category convertCategoryValue(String value) {

		if (value == null) {
			return null;
		}

		/*
		 * se existir uma categoria relacionada ao valor, retorna a categoria que e do
		 * tipo Category, caso contrario retorna uma excecao
		 */
		return switch (value) {
		case "Front-end" -> Category.FRONTEND;
		case "Back-end" -> Category.BACKEND;
		default -> throw new IllegalArgumentException("Categoria inválida: " + value);
		};
	}
}
