package br.com.rsds.crudspringlistcourses.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.rsds.crudspringlistcourses.dto.CourseDTO;
import br.com.rsds.crudspringlistcourses.dto.mapper.CourseMapper;
import br.com.rsds.crudspringlistcourses.exception.RecordNotFoundException;
import br.com.rsds.crudspringlistcourses.model.Course;
import br.com.rsds.crudspringlistcourses.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/* e uma especializacao de @Component e permite que spring detecte essa classe crie a instancia automaticamente para 
 * ela ser usada no controle de dependencia 
 */
@Service
@Validated
public class CourseService {
	private final CourseRepository coursesRepository;
	private final CourseMapper courseMapper;

	/*
	 * nao faz muito sentido usar o @AllArgsConstructor so para fazer a injecao da
	 * classe CoursesRepository no CoursesService, por isso a injecao e feita dessa
	 * forma usando o construtor
	 */
	public CourseService(CourseRepository coursesRepository, CourseMapper courseMapper) {
		this.coursesRepository = coursesRepository;
		this.courseMapper = courseMapper;
	}

//	public PageDTO list(@PositiveOrZero int pageNumber, @Positive @Max(10) int pageSize) {
//		Page<Course> page = coursesRepository.findAll(PageRequest.of(pageNumber, pageSize));
//		List<CourseDTO> coures = page.get().map(courseMapper::toDto).collect(Collectors.toList());
//		return new PageDTO(coures, page.getTotalElements(), page.getTotalPages());
//	}

	public List<CourseDTO> list() {
//		.stream() utilizado em lista, como isso pode ser feita uma cao em cada elemento da lista 
		return coursesRepository.findAll().stream().map(courseMapper::toDto).collect(Collectors.toList());
	}

	/*
	 * as validacoes nao foram removidas porque futuramente pode ter um outro
	 * Controller que possa chamar esses mesmo metodo
	 */
	public CourseDTO FindbyId(@NotNull @Positive Long id) {
		return coursesRepository.findById(id).map(courseMapper::toDto)
				.orElseThrow(() -> new RecordNotFoundException(id));
	}

//	recebe um DTO, converte para Etity .toEntity, salva no banco .save e retorna um DTO .toDTO
	public CourseDTO create(@Valid @NotNull CourseDTO courseDTO) {
		return courseMapper.toDto(coursesRepository.save(courseMapper.toEntity(courseDTO)));
	}

//	foi removido tudo que nao e do servico: ResponseEntity, @PathVariable
	public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO courseDTO) {
		return coursesRepository.findById(id).map(recordFound -> {

			Course course = courseMapper.toEntity(courseDTO);

			recordFound.setName(courseDTO.name());
			recordFound.setCategory(courseMapper.convertCategoryValue(courseDTO.category()));

			/*
			 * essa forma apresenta erro quando excluir uma aula do curso porque nao mantem
			 * a referencia da lista criada na entidade Course
			 */
//			recordFound.setLessons(course.getLessons());

			/*
			 * forma correta: remove os registros do lista de lessons que veio do banco de
			 * dados, matento a referencia da lista
			 */
			recordFound.getLessons().clear();

//			adiciona os novos registros atualizados na lista matendo a referencia da lista
			course.getLessons().forEach(recordFound.getLessons()::add);

			return courseMapper.toDto(coursesRepository.save(recordFound));
		}).orElseThrow(() -> new RecordNotFoundException(id));
	}

	public void Delete(@NotNull @Positive Long id) {
		coursesRepository.delete(coursesRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
	}
}