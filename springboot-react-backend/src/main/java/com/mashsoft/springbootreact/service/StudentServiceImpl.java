package com.mashsoft.springbootreact.service;

import com.mashsoft.springbootreact.exception.StudentAlreadyExistsException;
import com.mashsoft.springbootreact.exception.StudentNotFoundException;
import com.mashsoft.springbootreact.model.Student;
import com.mashsoft.springbootreact.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    @Override
    public Student addStudent(Student student) {
        if (studentAlreadyExists(student.getEmail())){
             throw new StudentAlreadyExistsException(student.getEmail()+ " already exists!");
        }

        return studentRepository.save(student);
    }


    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student updateStudent, Long id) {

        return studentRepository.findById(id).map(student -> {
            student.setDepartment( updateStudent.getDepartment());
            student.setEmail( updateStudent.getEmail());
            student.setFirstName(updateStudent.getFirstName());
            student.setFirstName( updateStudent.getFirstName());
            return studentRepository.save(student);
        }).orElseThrow(()-> new StudentNotFoundException("this student could not be found"));
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(()-> new StudentNotFoundException("No student found with the Id :"+id));
    }

    @Override
    public void deleteStudent(Long id) {
         if(!studentRepository.existsById(id)){
             throw  new StudentNotFoundException("Sorry Student Not found with Id:"+id);
         }
         studentRepository.deleteById(id);

    }
    private boolean studentAlreadyExists(String email) {
        return  studentRepository.findByEmail(email).isPresent();
    }

}
