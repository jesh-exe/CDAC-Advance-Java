package com.app.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.StudentDao;
import com.app.dto.StudentCloneResponseDto;
import com.app.dto.StudentRequestDto;
import com.app.dto.StudentResponseDto;
import com.app.entities.Student;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentDao stdDao;
	@Autowired
	private ModelMapper mapper;
	
	public StudentServiceImpl() {
		System.out.println("Student Service Class Initiated!");
	}

	@Override
	public StudentResponseDto addStudent(StudentRequestDto requestDto) {
		Student studObj = mapper.map(requestDto, Student.class);
		System.out.println(studObj);
		studObj = stdDao.save(studObj);
		return mapper.map(studObj, StudentResponseDto.class);
	}

	@Override
	public StudentCloneResponseDto getStudentByName(String firstName) {
		Student obj = stdDao.findByFirstName(firstName).orElseThrow(() -> new RuntimeException("Student Name Does Not Exists!"));
		return mapper.map(obj, StudentCloneResponseDto.class);
	}

	@Override
	public void deleteStudentByRoll(Integer roll) {
		if(!stdDao.existsById(roll))
			throw new RuntimeException("Student Does Not Exists!");
		stdDao.deleteById(roll);
	}
	
	
}
