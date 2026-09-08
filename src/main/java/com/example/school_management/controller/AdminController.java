package com.example.school_management.controller;

import com.example.school_management.dto.CourseDTO;
import com.example.school_management.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

        List<Integer> teachersIds = schoolService.getAllTeachersIds();

        model.addAttribute("teachersIds", teachersIds);

        CourseDTO courseDTO = new CourseDTO();
        model.addAttribute("courseDTO", courseDTO);

        return "/admin/createCourse";
    }
}
