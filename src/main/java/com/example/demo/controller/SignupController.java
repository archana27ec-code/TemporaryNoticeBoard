package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Student;
import com.example.demo.repository.StudentRepository;

@Controller
public class SignupController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/signup")
    public String displaySignup() {
        return "redirect:/register.html";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String studentname,
                         @RequestParam String email,
                         @RequestParam String password) {

        Optional<Student> existingStudent =
                studentRepository.findByEmail(email);

        if (existingStudent.isPresent()) {
            return "redirect:/register.html";
        }

        Student student = new Student();

        student.setStudentname(studentname);
        student.setEmail(email);
        student.setPassword(password);

        studentRepository.save(student);

        return "redirect:/index.html";
    }
}