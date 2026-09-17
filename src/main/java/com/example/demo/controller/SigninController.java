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
public class SigninController {

    @Autowired
    private StudentRepository studentRepository;

    // When opening localhost:8083
    @GetMapping("/")
    public String home() {
        return "redirect:/signin.html";
    }

    // Open Sign In page
    @GetMapping("/signin")
    public String displaySignin() {
        return "redirect:/signin.html";
    }

    // Process Sign In
    @PostMapping("/signin")
    public String signin(@RequestParam String email,
                         @RequestParam String password) {

        Optional<Student> student =
                studentRepository.findByEmail(email);

        if (student.isPresent() &&
            student.get().getPassword().equals(password)) {

            return "redirect:/index.html";
        }

        return "redirect:/signin.html";
    }
}