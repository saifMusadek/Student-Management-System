package com.studentmanagement.controller;

import com.studentmanagement.entity.Student;
import com.studentmanagement.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    // handler method to handle list students and return model and view

    @RequestMapping("/students")
    public String listStudents(Model model){
        System.out.println("here");
        model.addAttribute("students",studentService.getAllStudents());
        return "students";
    }


    @RequestMapping("/students/new")
    public String createStudentForm(Model model){
        //Create student object to hold student from data
        Student student = new Student();
        model.addAttribute("student",student);
        return "create_student";
    }


    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student){

        studentService.saveStudent(student);
        return "redirect:/students";
    }


    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model){
        //System.out.println("here-control");
        model.addAttribute("student",studentService.getStudentbyId(id));

        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student, Model model){

        //get student student from database by id
        Student existingStudent = studentService.getStudentbyId(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());

        // save updated student object

        studentService.updateStudent(existingStudent);
        return "redirect:/students";

    }


    //method to handle delete request
    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteStudentbyId(id);
        return "redirect:/students";
    }


}
