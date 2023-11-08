package br.com.rsds.crudspringlistcourses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rsds.crudspringlistcourses.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
