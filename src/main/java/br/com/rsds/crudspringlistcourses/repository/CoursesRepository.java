package br.com.rsds.crudspringlistcourses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rsds.crudspringlistcourses.model.CoursesList;

@Repository
public interface CoursesRepository extends JpaRepository<CoursesList, Long> {

}
