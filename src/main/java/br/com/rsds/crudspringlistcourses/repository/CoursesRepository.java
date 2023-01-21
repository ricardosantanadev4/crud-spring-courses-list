package br.com.rsds.crudspringlistcourses.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rsds.crudspringlistcourses.model.CoursesList;

public interface CoursesRepository extends JpaRepository<CoursesList, Long> {

}
