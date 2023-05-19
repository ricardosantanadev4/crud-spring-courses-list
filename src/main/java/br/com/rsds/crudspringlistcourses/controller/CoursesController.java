package br.com.rsds.crudspringlistcourses.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.rsds.crudspringlistcourses.dto.CourseDTO;
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
	public @ResponseBody List<CourseDTO> list() {
		return coursesService.list();
	}

	@GetMapping("/{id}")
//	Long e do tipo objeto entao ele pode ser null por esse motivo foi adicionado @NotNull
//	como o id e um numero, e ele pode ser positivo ou negativo por  esse motivo foi adicionado @Positive porque o id nao pode ser negativo
	public CourseDTO FindbyId(@PathVariable @NotNull @Positive Long id) {
		return coursesService.FindbyId(id);
	}

	@PostMapping
//	@Valid verifica se o json recebido e valido de acordo com as validacoes da API do back-end. se ele for valido prossegue com a requisicao se ele for invalido nao procegue com a requisicao
	@ResponseStatus(code = HttpStatus.CREATED)
	public CourseDTO create(@RequestBody @Valid CourseDTO record) {
		return coursesService.create(record);
	}

	@PutMapping("/{id}")
	public CourseDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid CourseDTO record) {
		return coursesService.update(id, record);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void Delete(@PathVariable @NotNull @Positive Long id) {
		coursesService.Delete(id);
	}
}