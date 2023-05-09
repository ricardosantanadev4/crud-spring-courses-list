package br.com.rsds.crudspringlistcourses.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.rsds.crudspringlistcourses.exception.RecordNotFoundException;

// @RestControllerAdvice indica que essa classe que vai dizer para todos os Restcontrollers o que fazer com as exececoes
@RestControllerAdvice
public class AplicationControllerAdvace {

//	no lugar de mostrar o erro 500 vai mostrar uma mensagem personalizada
	@ExceptionHandler(RecordNotFoundException.class)
	public String HandleNotFoundException(RecordNotFoundException ex) {
		return ex.getMessage();
	}
}
