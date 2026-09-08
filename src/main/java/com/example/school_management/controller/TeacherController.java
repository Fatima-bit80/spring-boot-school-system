package com.example.school_management.controller;

import com.example.school_management.dto.GradeRow;
import com.example.school_management.dto.GradesForm;
import com.example.school_management.entity.Course;
import com.example.school_management.entity.Enrollment;
import com.example.school_management.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/courses/{teacherId}")
    public String courses(@PathVariable("teacherId") int teacherId, Model model) {
        List<Course> courses = schoolService.findCoursesByTeacherId(teacherId);
        model.addAttribute("courses", courses);
        model.addAttribute("teacherId", teacherId);

        return "teacher/courses";
    }

    @GetMapping("/courseEnrollments/{courseCode}")
    public String courseEnrollments(@PathVariable("courseCode") String code, Model model) {


        List<Enrollment>  enrollments = schoolService.findEnrollmentsOfCourse(code);
        model.addAttribute("enrollments", enrollments);



        GradesForm gradesForm = new GradesForm();
        gradesForm.setCode(code);
        gradesForm.setRows(new ArrayList<>());
        for(Enrollment enrollment : enrollments){
      gradesForm.getRows().add(new GradeRow(enrollment.getId(),enrollment.getGrade()));
        }

        model.addAttribute("gradesForm", gradesForm);

        return "teacher/courseEnrollments";

    }



    @PostMapping("/updateGrades")
    public String updateGrades(@ModelAttribute("gradesForm") GradesForm gradesForm) {

  
schoolService.updateGrades(gradesForm);


        return "redirect:/teacher/courseEnrollments/"+gradesForm.getCode();

    }


}