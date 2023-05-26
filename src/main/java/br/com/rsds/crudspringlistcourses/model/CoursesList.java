package br.com.rsds.crudspringlistcourses.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import br.com.rsds.crudspringlistcourses.enums.Category;
import br.com.rsds.crudspringlistcourses.enums.converters.CategoryConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
// @Table(name="Cursos")
@Data
// @SQLDelete(sql = "") ececuta o camando sql passado na string toda vez que o metodo Delete do Repostoriry for chamado ex: deleteById(id) 
@SQLDelete(sql = "UPDATE COURSES_LIST SET status='Inativo' WHERE id=?")
// filtra somente os cursos com status ativo na hora do get
@Where(clause = "status = 'Ativo'")
public class CoursesList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

//	@NotBlank o campo nao pode ser nullo e deve conter pelo menos um caractere que nao seja um espaco em branco
	@NotBlank
	@Length(min = 5, max = 100)
//	@NotNull  o campo nao pode ser nullo
	@NotNull
	@Column(length = 100, nullable = false)
	private String name;

	@NotNull
	@Column(length = 10, nullable = false)
	@Convert(converter = CategoryConverter.class)
	private Category category;

	@NotBlank
	@NotNull
	@Length(max = 10)
	@Pattern(regexp = "Ativo|Inativo")
	@Column(length = 10, nullable = false)
	private String status = "Ativo";
}
