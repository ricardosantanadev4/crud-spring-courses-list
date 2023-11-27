package br.com.rsds.crudspringlistcourses.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import br.com.rsds.crudspringlistcourses.enums.Category;
import br.com.rsds.crudspringlistcourses.enums.validation.ValueOfEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CourseDTO(Long id, @NotBlank @NotNull @Length(min = 5, max = 100) String name,
		@NotNull @Length(max = 10) @ValueOfEnum(enumClass = Category.class) String category,
		/*
		 * para exibir somente o array no console e necessario que o atributo da
		 * interface do front-end tenha o mesmo nome do atribudo do banck-end
		 */
		@NotNull @NotEmpty @Valid List<LessonDTO> lessons) {

}
