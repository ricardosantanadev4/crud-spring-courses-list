package br.com.rsds.crudspringlistcourses.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import br.com.rsds.crudspringlistcourses.enums.Category;
import br.com.rsds.crudspringlistcourses.enums.Status;
import br.com.rsds.crudspringlistcourses.enums.converters.CategoryConverter;
import br.com.rsds.crudspringlistcourses.enums.converters.StatusConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
// @Table(name="Cursos")
@Data
// @SQLDelete(sql = "") executa o camando sql passado na string toda vez que o metodo Delete do Repostoriry for chamado ex: deleteById(id) 
@SQLDelete(sql = "UPDATE COURSES SET status='Inativo' WHERE id=?")
// filtra somente os cursos com status ativo na hora do get
@Where(clause = "status = 'Ativo'")
public class Course {
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
//	@Convert(converter = CategoryConverter.class) aplica o conversor que vai converter esse atributo para um coluna valida e assim poder ser salvo no banco de dados
//  ou converte essa informacao da coluna do banco de dados para Enum quando é feito um get no banco
	@Convert(converter = CategoryConverter.class)
	private Category category;

	@NotNull
	@Column(length = 10, nullable = false)
	@Convert(converter = StatusConverter.class)
	private Status status = Status.ACTIVE;

//	@OneToMany indica a maneira que o array vai ser persistido no banco de dados
//	cascade = CascadeType.ALL sempre que ocorrer uma mudanca na entidade ele foi adicionando, essas mudancas serao passadas para as classes filhas dessa etidade
//	orphanRemoval = true quando algo for removido na endidade pair a remacao tambem vai ser feita na etidade filha ex: quando remover um curso em Courses as aulas desse curso serao removidas em Lesson
//	mappedBy = "Courses" define que a classe Course e dona desse relacionamento. necessario para nao fazer um update quando for adicionar a coluna course_id 
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
//	@JoinColumn(name = "course_id") cria uma coluna com o nome course_id na tabela da entidade Lesson com a chave primaria do curso que nesse caso e o  id, se nao usar o joinColumn o sistema vai criar 3 tabelas que nao e recomendado para @OneToMany, so e recomendo para relacao de muitos para muitos que nao e o caso 
//	@JoinColumn(name = "course_id")
	private List<Lesson> lessons = new ArrayList<>();
}
