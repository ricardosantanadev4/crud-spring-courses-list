package br.com.rsds.crudspringlistcourses;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.rsds.crudspringlistcourses.model.CoursesList;
import br.com.rsds.crudspringlistcourses.repository.CoursesRepository;

@SpringBootApplication
public class CrudSpringListCoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringListCoursesApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(CoursesRepository coursesRepository) {
		return args -> {
			coursesRepository.deleteAll();
			CoursesList course = new CoursesList();
			course.setName("Angular");
			course.setCategory("Front-end");
			coursesRepository.save(course);
		};
	}
}
