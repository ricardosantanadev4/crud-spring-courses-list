package br.com.rsds.crudspringlistcourses.model;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Lesson {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	@NotNull
	@Length(min = 5, max = 100)
	@Column(length = 100, nullable = false)
	private String name;

	@NotBlank
	@NotNull
	@Length(min = 10, max = 11)
	@Column(length = 11, nullable = false)
	private String youtubeURL;

	@NotNull
//	 @ManyToOne quando e adicionado o mappedBy na classe principal e necessrio criar essa anotacao indicando o relacionamento entre as classes, nesse caso sao varias aulas para um curso 
//	fetch = FetchType.LAZY indica que esse mapeamento so vai ser carregado quando for chamado .get dessa licao
//	optional = true indicia que a coluna course_id e obrigatoria, e ela vai ter que esta sempre populada
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "course_id", nullable = false)
//	para evitar o update n+1 foi feito o @ManyToOne nesse atributo, como isso gerou uma dependecia circular, para evitar esse problema e adicionado @JsonProperty(access = Access.WRITE_ONLY), pois quando chegar nesse ponto o sistema nao vai fazer um novo select evitando o loop
	@JsonProperty(access = Access.WRITE_ONLY)
	private Course course;

	public Lesson() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYoutubeURL() {
		return youtubeURL;
	}

	public void setYoutubeURL(String youtubeURL) {
		this.youtubeURL = youtubeURL;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Lesson [id=" + id + ", name=" + name + ", youtubeURL=" + youtubeURL + ", course=" + course + "]";
	}

}