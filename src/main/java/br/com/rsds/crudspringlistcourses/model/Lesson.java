package br.com.rsds.crudspringlistcourses.model;

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
import lombok.Data;

@Data
@Entity
public class Lesson {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 11, nullable = false)
	private String youtubeURL;

//	 @ManyToOne quando e adicionado o mappedBy na classe principal e necessrio criar essa anotacao indicando o relacionamento entre as classes, nesse caso sao varias aulas para um curso 
//	fetch = FetchType.LAZY indica que esse mapeamento so vai ser carregado quando for chamado .get dessa licao
//	optional = true indicia que a coluna course_id e obrigatoria, e ela vai ter que esta sempre populada
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "course_id", nullable = false)
//	para evitar o update n+1 foi feito o @ManyToOne nesse atributo, como isso gerou uma dependecia circular, para evitar esse problema e adicionado @JsonProperty(access = Access.WRITE_ONLY), pois quando chegar nesse ponto o sistema nao vai fazer um novo select evitando o loop
	@JsonProperty(access = Access.WRITE_ONLY)
	private Course course;
}