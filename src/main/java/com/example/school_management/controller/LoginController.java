package com.example.school_management.controller;

import com.example.school_management.dto.StudentDTO;
import com.example.school_management.dto.TeacherDTO;
import com.example.school_management.entity.Member;
import com.example.school_management.entity.Student;
import com.example.school_management.entity.Teacher;
import com.example.school_management.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    SchoolService schoolService;

    @Autowired
    public LoginController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/showLoginPage")
    public String showLoginPage() {
        return "plain-login";
    }

    @GetMapping("/StudentCreateAccount")
    public String showStudentCreateAccountPage(Model model) {

        StudentDTO studentDTO = new StudentDTO(new Student(), new Member("ROLE_STUDENT",1));
        model.addAttribute("studentDTO", studentDTO);
        return "create-account-student";
    }

    @GetMapping("/TeacherCreateAccount")
    public String showTeacherCreateAccountPage(Model model) {

        TeacherDTO teacherDTO = new TeacherDTO(new Teacher(), new Member("ROLE_TEACHER",1));
        model.addAttribute("teacherDTO", teacherDTO);
        return "create-account-teacher";
    }


    @PostMapping("/createTeacher")
    public String createTeacher(@ModelAttribute("teacherDTO") TeacherDTO teacherDTO) {
        Teacher teacher = teacherDTO.getTeacher();
        Member member = teacherDTO.getMember();

        String password ="{noop}"+  member.getPassword();
        member.setPassword(password);

        teacher.setMember(member);

        schoolService.saveMember(member);
        schoolService.saveTeacher(teacher);



        return "redirect:/";

    }

    @PostMapping("/createStudent")
    public String createStudent(@ModelAttribute("studentDTO") StudentDTO studentDTO){

        Student student = studentDTO.getStudent();
        Member member = studentDTO.getMember();

        String password ="{noop}"+  member.getPassword();
        member.setPassword(password);


        System.out.println("member: "+member);
        schoolService.saveMember(member);
        student.setMember(member);
        schoolService.saveStudent(student);

        System.out.println("id ="+member.getMemberId());

        return "redirect:/";

    }
}
