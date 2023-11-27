package br.com.rsds.crudspringlistcourses.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.rsds.crudspringlistcourses.exception.RecordNotFoundException;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class AplicationControllerAdvace {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(RecordNotFoundException.class)
	public String HandleNotFoundException(RecordNotFoundException ex) {
		return ex.getMessage();
	}

	/*
	 * a excecao MethodArgumentNotValidException vai ser disparada no console toda
	 * vez que tiver algum erro de validacao nas validacoes do CourseDTO
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	esse metodo exibe todos os erros de validacoes retornando uma mensagen especifica de acordo com cada erro de validacao
	public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + " " + error.getDefaultMessage())
				.reduce("", (acc, error) -> acc + error + "\n");
	}

//	e disparada quando a algum erro de validacao de contrait ex: o metodo tem a @Positive e passado um numero negativo na requisicao
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleConstraintViolationException(ConstraintViolationException ex) {
		return ex.getConstraintViolations().stream().map(error -> error.getPropertyPath() + " " + error.getMessage())
				.reduce("", (acc, error) -> acc + error + "\n");
	}

//  quando e digitado uma String na url no lugar do id na hora do get, essa excecao e retornada
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		if (ex != null && ex.getRequiredType() != null) {
			String type = ex.getRequiredType().getName();
			String[] typeParts = type.split("\\.");
			String typeName = typeParts[typeParts.length - 1];
			return ex.getName() + " Shold be of type " + typeName;
		}
		return "Argument type not invalid";
	}
}