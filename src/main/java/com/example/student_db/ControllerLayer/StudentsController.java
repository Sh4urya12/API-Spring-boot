package com.example.student_db.ControllerLayer;

import com.example.student_db.classlayer.Students;
import com.example.student_db.RepoLayer.StudentsRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class StudentsController
{
    @Autowired
    StudentsRepository studentRepository;

    @PostMapping("/students")
    public Students addStudent(@RequestBody Students student)
    {
        return studentRepository.save(student);
    }

    @GetMapping("/students")
    public List<Students> getStudents()
    {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public Optional<Students> getStudentById(@PathVariable Integer id)
    {
        return studentRepository.findById(id);
    }

    @PutMapping("/students/{id}")
    public String updateStudent(@PathVariable Integer id, @RequestBody Students updatedStudent)
    {
        Optional<Students> optionalStudent = studentRepository.findById(id);

        if(optionalStudent.isPresent())
        {
            Students existingStudent = optionalStudent.get();

            Optional.ofNullable(updatedStudent.getName())
                    .ifPresent(existingStudent::setName);

            Optional.ofNullable(updatedStudent.getDepartment())
                    .ifPresent(existingStudent::setDepartment);

            studentRepository.save(existingStudent);

            return "Student Updated Successfully";
        }
        return "Student Not Found";
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable Integer id)
    {
        if(studentRepository.existsById(id))
        {
            studentRepository.deleteById(id);
            return "Student Deleted Successfully";
        }
        return "Student Not Found";
    }
}

