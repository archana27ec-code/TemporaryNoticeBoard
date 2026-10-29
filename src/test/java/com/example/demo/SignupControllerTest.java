package com.example.demo;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.SignupController;
import com.example.demo.domain.Student;
import com.example.demo.repository.StudentRepository;

@WebMvcTest(SignupController.class)
class SignupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentRepository studentRepository;

    @Test
    void signup_newStudent_isSaved_andRedirectsToIndex() throws Exception {
        when(studentRepository.findByEmail("archana@example.com"))
                .thenReturn(Optional.empty());

        mockMvc.perform(post("/signup")
                        .param("studentname", "Archana")
                        .param("email", "archana@example.com")
                        .param("password", "pw123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index.html"));

        verify(studentRepository).save(any(Student.class));
    }
}