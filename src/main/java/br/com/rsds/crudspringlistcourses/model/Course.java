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

//	@OneToMany indica a maneira que o array vai ser persistido no banco de dados, um curso tem varias aulas por isso @OneToMany
//	cascade = CascadeType.ALL sempre que ocorrer uma mudanca na entidade ele foi adicionando, essas mudancas serao passadas para as classes filhas dessa etidade, sem nao for adicionada va apresentar TransientPropertyValueException
//	orphanRemoval = true quando um curso for removido as a aulas relacionadas a esse curso que ficarem orfans tambem serao removidas 
//	mappedBy = "Courses" define que a classe Course e dona desse relacionamento informando na String do mappedBy o nome da coluna na classe filha que tem a chave primaria da classe principal . com isso e possivel criar essa coluna na propria classe filha sem precisar de fazer um update para criar essa coluna, tambem evita do sistema criar uma terceira tabela 
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
//	@JoinColumn(name = "course_id") quando adicionado na classe principal no lugar do mappedBy ele precisa fazer um update para criar a coluna na classe filha que tem a chave primaria da classe principal, por isso nao e recomendado adicionar na classe principal
	private List<Lesson> lessons = new ArrayList<>();
}
