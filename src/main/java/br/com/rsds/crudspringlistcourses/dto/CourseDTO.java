package br.com.rsds.crudspringlistcourses.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import br.com.rsds.crudspringlistcourses.model.Lesson;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CourseDTO(Long id, @NotBlank @NotNull @Length(min = 5, max = 100) String name,
		@NotBlank @NotNull @Length(max = 10) @Pattern(regexp = "Back-End|Front-End") String category,
		List<Lesson> lesson) {

}
