package br.com.rsds.crudspringlistcourses.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rsds.crudspringlistcourses.model.CoursesList;
import br.com.rsds.crudspringlistcourses.repository.CoursesRepository;
import br.com.rsds.crudspringlistcourses.service.CoursesService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// @Validated e necessario para as validacoes @NotNull @Positive funcionar 
@Validated
@RestController
@RequestMapping("/api/courses")
//@AllArgsConstructor

public class CoursesController {
//	private final CoursesRepository coursesRepository;
	private final CoursesService coursesService;

	public CoursesController(CoursesRepository coursesRepository, CoursesService coursesService) {
//		this.coursesRepository = coursesRepository;
		this.coursesService = coursesService;
	}

	@GetMapping
	public @ResponseBody List<CoursesList> list() {
		List<CoursesList> course = coursesService.list();
		return course;
	}

	@GetMapping("/{id}")
//	Long e do tipo objeto entao ele pode ser null por esse motivo foi adicionado @NotNull
//	como o id e um numero, e ele pode ser positivo ou negativo por  esse motivo foi adicionado @Positive porque o id não pode ser negativo
	public ResponseEntity<CoursesList> GetbyId(@PathVariable @NotNull @Positive Long id) {
		return coursesService.GetbyId(id).map(course -> ResponseEntity.ok().body(course))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
//	@Valid verifica se o json recebido e valido de acor com as validacoes da api do back-end. se ele for valido prossegue com a requisicao se ele for invalido nao procegue com a requisicao
	public CoursesList create(@RequestBody @Valid CoursesList record) {
		return coursesService.create(record);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CoursesList> update(@PathVariable @NotNull @Positive Long id,
			@RequestBody @Valid CoursesList record) {
		return coursesService.GetbyId(id).map(recordFound -> {
			recordFound.setName(record.getName());
			recordFound.setCategory(record.getCategory());
			CoursesList course = coursesService.create(recordFound);
			return ResponseEntity.ok(course);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> Delete(@PathVariable Long id) {
		if (coursesService.Delete(id)) {
			return ResponseEntity.noContent().<Void>build();
		}
		return ResponseEntity.notFound().build();
	}
}