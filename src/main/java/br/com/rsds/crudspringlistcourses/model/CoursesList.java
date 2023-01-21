package br.com.rsds.crudspringlistcourses.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CoursesList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "NAME", nullable = false, length = 200)
	private String name;
	@Column(name = "CATEGORY", nullable = false, length = 10)
	private String category;
}
