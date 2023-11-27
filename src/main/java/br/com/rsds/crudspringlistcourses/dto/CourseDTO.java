package br.com.rsds.crudspringlistcourses.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CourseDTO(
		Long id, 
		@NotBlank @NotNull @Length(min = 5, max = 100) String name,
		@NotNull @Length(max = 10) @Pattern(regexp = "Back-end|Front-end") String category,
		/*
		 * para exibir somente o array no console e necessario que o atributo da
		 * interface do front-end tenha o mesmo nome do atribudo do banck-end
		 */
		@NotNull @NotEmpty @Valid List<LessonDTO> lessons) {

}
