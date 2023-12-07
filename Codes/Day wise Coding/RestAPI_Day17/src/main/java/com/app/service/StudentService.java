package com.app.service;

import java.util.Optional;

import com.app.dto.StudentCloneResponseDto;
import com.app.dto.StudentRequestDto;
import com.app.dto.StudentResponseDto;
import com.app.entities.Student;

public interface StudentService {

	StudentResponseDto addStudent(StudentRequestDto requestDto);
	StudentCloneResponseDto getStudentByName(String firstName);
	void deleteStudentByRoll(Integer roll);
	
}
