package br.com.rsds.crudspringlistcourses.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rsds.crudspringlistcourses.model.CoursesList;
import br.com.rsds.crudspringlistcourses.repository.CoursesRepository;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor

public class CoursesController {
	CoursesRepository coursesRepository;

	@GetMapping
	public List<CoursesList> list() {
		List<CoursesList> course = coursesRepository.findAll();
		return course;
	}
}
