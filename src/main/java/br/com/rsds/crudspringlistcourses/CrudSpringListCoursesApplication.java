package br.com.rsds.crudspringlistcourses;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.rsds.crudspringlistcourses.enums.Category;
import br.com.rsds.crudspringlistcourses.model.Course;
import br.com.rsds.crudspringlistcourses.model.Lesson;
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

			Course course = new Course();
			course.setName("Angular");
			System.out.println("inserindo a categoria");
			course.setCategory(Category.FRONTEND);
			System.out.println("Categoria setada em memoria: " + course.getCategory());

			Lesson l = new Lesson();
			l.setName("Introdução");
			l.setYoutubeURL("oEhX1TYckCo");
			l.setCourse(course);

//			como o objeto tipo Course foi setado primeiro, o id foi desse objeto foi gerado automaticamente,com isso e possivel adicionar o objeto courser no array
			course.getLessons().add(l);

			Lesson l1 = new Lesson();
			l1.setName("Angular");
			l1.setYoutubeURL("oEhX1TYckC1");
			l1.setCourse(course);

			course.getLessons().add(l1);
			coursesRepository.save(course);
		};
	}
}
