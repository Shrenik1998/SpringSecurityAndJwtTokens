package com.scaler.springsecurityandjwttokens.controller;

import com.scaler.springsecurityandjwttokens.models.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private List<Student> students = new ArrayList<>(List.of(
            new Student("student1",1),
            new Student("student2",2),
            new Student("student3",3)
    ));

    @GetMapping
    public List<Student> getStudents() {
        return students;
    }

    @PostMapping
    public Student insertStudents(@RequestBody Student student) {

        students.add(student);
        return student;
    }

}
