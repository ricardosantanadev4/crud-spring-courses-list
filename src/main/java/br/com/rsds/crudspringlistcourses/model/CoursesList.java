package br.com.rsds.crudspringlistcourses.model;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table
@Data
public class CoursesList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

//	@NotBlank o campo nao pode ser nullo e deve conter pelo menos um caractere que nao seja um espaco em branco
	@NotBlank
	@Length(min = 5, max = 100)
//	@NotNull  o campo nao pode ser nullo e nao pode ser vazio
	@NotNull
	@Column(length = 100, nullable = false)
	private String name;

	@NotBlank
	@NotNull
	@Length(max = 10)
//	permite apenas o valor especificado na expressao regular nesse caso Back-end ou Front-end
	@Pattern(regexp = "Back-end|Front-end")
	@Column(length = 10, nullable = false)
	private String category;
}
