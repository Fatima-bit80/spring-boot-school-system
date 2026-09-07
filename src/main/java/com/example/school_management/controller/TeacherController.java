package com.example.school_management.controller;

import com.example.school_management.entity.Enrollment;
import com.example.school_management.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("teacher")
public class TeacherController {

    private SchoolService schoolService;

    @Autowired
    public TeacherController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }



    @GetMapping("/requests/{teacherId}")
    public String requestTeacher(@PathVariable("teacherId") int teacherId, Model model) {

        List<Enrollment> requests = schoolService.findRequestsForTeacher(teacherId);

        model.addAttribute("requests", requests);
        model.addAttribute("teacherId", teacherId);

        return "teacher/requests";
    }

    @GetMapping("/accept")
    public String acceptRequest(@RequestParam("requestId") int requestId, @RequestParam("teacherId") int teacherId) {
        schoolService.acceptRequest(requestId);

        return "redirect:/teacher/requests/" + teacherId;
    }


    @GetMapping("/reject")
    public String rejectRequest(@RequestParam("requestId") int requestId, @RequestParam("teacherId") int teacherId) {

        schoolService.deleteEnrollment(requestId);

        return "redirect:/teacher/requests/" + teacherId;

    }

}