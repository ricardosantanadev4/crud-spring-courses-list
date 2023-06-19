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
			course.setCategory(Category.BACKEND);
			System.out.println("Categoria setada em memoria: " + course.getCategory());

			Lesson lesson = new Lesson();
			lesson.setName("Introdução");
			lesson.setYoutubeURL("oEhX1TYckCo");
//			como o objeto tipo Course foi setado primeiro no banco de dados, o id foi desse objeto foi gerado automaticamente,com isso e possivel setar o id do Obejeto na coluna course_id do mapeamento realacional da Classe Lesson
//			dessa forma so e feito 2 insert's no banco de dados, no lugar de 3 aumentando o desempenho
			lesson.setCourse(course);
			course.getLessons().add(lesson);
			coursesRepository.save(course);
		};
	}
}
