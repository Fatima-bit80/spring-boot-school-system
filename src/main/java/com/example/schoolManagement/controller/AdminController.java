package com.example.schoolManagement.controller;

import com.example.schoolManagement.dto.CourseDTO;
import com.example.schoolManagement.entity.Teacher;
import com.example.schoolManagement.service.SchoolService;
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
