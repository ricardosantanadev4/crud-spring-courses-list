package br.com.rsds.crudspringlistcourses.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.rsds.crudspringlistcourses.dto.CourseDTO;
import br.com.rsds.crudspringlistcourses.exception.RecordNotFoundException;
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

	public List<CourseDTO> list() {
		List<CoursesList> courses = coursesRepository.findAll();
		List<CourseDTO> dto = new ArrayList<>(courses.size());
		for (CoursesList course : courses) {
			CourseDTO dtos = new CourseDTO(course.getId(), course.getName(), course.getCategory());
			dto.add(dtos);
		}
		return dto;
	}

//	as validacoes nao foram removidas porque futuramente pode ter um outro Controller que possa chamar esses mesmo método
	public CoursesList FindbyId(@PathVariable @NotNull @Positive Long id) {
		return coursesRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
	}

	public CoursesList create(@Valid CoursesList record) {
		return coursesRepository.save(record);
	}

//	foi removido tudo que nao e do servico: ResponseEntity, @PathVariable
	public CoursesList update(@NotNull @Positive Long id, @RequestBody @Valid CoursesList record) {
		return coursesRepository.findById(id).map(recordFound -> {
			recordFound.setName(record.getName());
			recordFound.setCategory(record.getCategory());
			return coursesRepository.save(recordFound);
		}).orElseThrow(() -> new RecordNotFoundException(id));
	}

	public void Delete(@PathVariable @NotNull @Positive Long id) {
		coursesRepository.delete(coursesRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
	}
}
