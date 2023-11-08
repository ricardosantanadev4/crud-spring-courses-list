package br.com.rsds.crudspringlistcourses.dto;

import java.util.List;

public record PageDTO(List<CourseDTO> coures, long tontalElements, int totalPage) {

}