package br.com.rsds.crudspringlistcourses.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.rsds.crudspringlistcourses.model.CoursesList;
import br.com.rsds.crudspringlistcourses.repository.CoursesRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// e uma especializacao de @Component e permite que spring detecte essa classe crie a instancia automaticamente para ela ser usada no controlle de dependencia
@Service
@Validated
public class CoursesService {
	private final CoursesRepository coursesRepository;

//	nao faz muito sentido usar o @AllArgsConstructor so para fazer a injecao da classe CoursesRepository no CoursesService, por isso a injecao e feita dessa forma usando o construtor
	public CoursesService(CoursesRepository coursesRepository) {
		this.coursesRepository = coursesRepository;
	}

	@GetMapping
	public List<CoursesList> list() {
		List<CoursesList> course = coursesRepository.findAll();
		return course;
	}

//	as validacoes nao foram removidas porque futuramente pode ter um outro Controller que possa chamar esses mesmo método
	@GetMapping("/{id}")
	public Optional<CoursesList> FindbyId(@PathVariable @NotNull @Positive Long id) {
		return coursesRepository.findById(id);
	}

	@PostMapping
	public CoursesList create(@Valid CoursesList record) {
		return coursesRepository.save(record);
	}

//	foi removido tudo que nao e do servico: ResponseEntity, @PathVariable
	@PutMapping("/{id}")
	public Optional<CoursesList> update(@NotNull @Positive Long id, @RequestBody @Valid CoursesList record) {
		return coursesRepository.findById(id).map(recordFound -> {
			recordFound.setName(record.getName());
			recordFound.setCategory(record.getCategory());
			return coursesRepository.save(recordFound);
		});
	}

	@DeleteMapping("/{id}")
	public Boolean Delete(Long id) {
		return coursesRepository.findById(id).map(course -> {
			coursesRepository.deleteById(id);
			return true;
		}).orElse(false);
	}
}
