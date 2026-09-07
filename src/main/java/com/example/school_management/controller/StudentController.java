package com.example.school_management.controller;

import com.example.school_management.entity.Course;
import com.example.school_management.entity.Enrollment;
import com.example.school_management.entity.Student;
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
@RequestMapping("/student")
public class StudentController {

    private SchoolService schoolService;
    @Autowired
    public  StudentController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/enrollment/{studentId}")
    public String enrollments(@PathVariable int studentId,Model model){
        System.out.println(model);
      List<Enrollment> enrollments = schoolService.findEnrollmentsOfStudent(studentId);
      model.addAttribute("enrollments",enrollments);
      model.addAttribute("studentId",studentId);

        return "student/enrollments";
    }

    @GetMapping("/dropout")
    public String dropouts(@RequestParam int enrollmentId,@RequestParam int studentId){
        schoolService.deleteEnrollment(enrollmentId);
        return "redirect:/student/enrollment/"+studentId;
    }

    @GetMapping("/enroll/{studentId}")
    public String enroll(@PathVariable int studentId,Model model){

        System.out.println("in     @GetMapping(\"/enroll/{studentId}\")\n");

        List<Course> availableCourses = schoolService.findAvailableCourses(studentId);
        model.addAttribute("availableCourses",availableCourses);
        model.addAttribute("studentId",studentId);

        return "student/enroll";
    }

    @GetMapping("/requestEnrollment")
    public String requestEnrollment(@RequestParam String courseCode,@RequestParam int studentId){

        schoolService.saveEnrollment(courseCode,studentId);

        return "redirect:/student/enroll/"+studentId;
    }
}
