package br.com.rsds.crudspringlistcourses.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.rsds.crudspringlistcourses.dto.CourseDTO;
import br.com.rsds.crudspringlistcourses.dto.mapper.CourseMapper;
import br.com.rsds.crudspringlistcourses.exception.RecordNotFoundException;
import br.com.rsds.crudspringlistcourses.repository.CoursesRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// e uma especializacao de @Component e permite que spring detecte essa classe crie a instancia automaticamente para ela ser usada no controlle de dependencia
@Service
@Validated
public class CoursesService {
	private final CoursesRepository coursesRepository;
	private final CourseMapper courseMapper;

//	nao faz muito sentido usar o @AllArgsConstructor so para fazer a injecao da classe CoursesRepository no CoursesService, por isso a injecao e feita dessa forma usando o construtor
	public CoursesService(CoursesRepository coursesRepository, CourseMapper courseMapper) {
		this.coursesRepository = coursesRepository;
		this.courseMapper = courseMapper;
	}

	public List<CourseDTO> list() {
//		.stream() para cada objeto da lista pode ser feita uma acao 
		return coursesRepository.findAll().stream().map(courseMapper::toDo).collect(Collectors.toList());
	}

//	as validacoes nao foram removidas porque futuramente pode ter um outro Controller que possa chamar esses mesmo método
	public CourseDTO FindbyId(@PathVariable @NotNull @Positive Long id) {
		return coursesRepository.findById(id).map(courseMapper::toDo)
				.orElseThrow(() -> new RecordNotFoundException(id));
	}

//	recebe um DTO, converte para Etity .toEntity, salva no banco .save e retorna um DTO .toDTO
	public CourseDTO create(@Valid @NotNull CourseDTO record) {
		return courseMapper.toDo(coursesRepository.save(courseMapper.toEntity(record)));
	}

//	foi removido tudo que nao e do servico: ResponseEntity, @PathVariable
	public CourseDTO update(@NotNull @Positive Long id, @RequestBody @Valid @NotNull CourseDTO record) {
		return coursesRepository.findById(id).map(recordFound -> {
			recordFound.setId(record.id());
			recordFound.setName(record.name());
			recordFound.setCategory(record.category());
			return courseMapper.toDo(recordFound);
		}).orElseThrow(() -> new RecordNotFoundException(id));
	}

	public void Delete(@PathVariable @NotNull @Positive Long id) {
		coursesRepository.delete(coursesRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
	}
}
