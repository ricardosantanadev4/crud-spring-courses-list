package br.com.rsds.crudspringlistcourses.model;

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

//	 @ManyToOne necessario  para nao fazer um update quando for adicionar a coluna course_id
//	sao varias aulas para um curso por isso @ManyToOne
//	fetch = FetchType.LAZY indica que esse mapeamento so vai ser carregado quando for chamado .get dessao licao
//	optional = true indicia que a coluna course_id e obrigatoria, e ela vai ter que esta sempre populada
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;
}
