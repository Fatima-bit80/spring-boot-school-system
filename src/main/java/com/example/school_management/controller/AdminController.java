package com.example.school_management.controller;

import com.example.school_management.dto.CourseDTO;
import com.example.school_management.entity.Course;
import com.example.school_management.entity.Teacher;
import com.example.school_management.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private SchoolService schoolService;
    @Autowired
    public AdminController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/createCourseForm")
    public String createCourseForm(Model model){

        List<Teacher> teachers = schoolService.getAllTeachers();

        model.addAttribute("teachers", teachers);

        CourseDTO courseDTO = new CourseDTO();
        model.addAttribute("courseDTO", courseDTO);

        return "/admin/createCourse";
    }

    @PostMapping("/createCourse")
    public String createCourse(@ModelAttribute("courseDTO") CourseDTO courseDTO){

        schoolService.saveCourse(courseDTO);
return "redirect:/";
    }
}
