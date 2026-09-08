package com.example.schoolManagement.controller;

import com.example.schoolManagement.dto.SchoolDTO;
import com.example.schoolManagement.entity.Member;
import com.example.schoolManagement.entity.Student;
import com.example.schoolManagement.entity.Teacher;
import com.example.schoolManagement.service.SchoolService;
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

        SchoolDTO studentDTO = new SchoolDTO( new Member("ROLE_STUDENT",1));
        studentDTO.setStudent(new  Student());
        model.addAttribute("studentDTO", studentDTO);
        return "create-account/create-account-student";
    }

    @GetMapping("/TeacherCreateAccount")
    public String showTeacherCreateAccountPage(Model model) {

        SchoolDTO teacherDTO = new SchoolDTO( new Member("ROLE_TEACHER",1));
        teacherDTO.setTeacher(new  Teacher());
        model.addAttribute("teacherDTO", teacherDTO);
        return "create-account/create-account-teacher";
    }


    @PostMapping("/createTeacher")
    public String createTeacher(@ModelAttribute("teacherDTO") SchoolDTO teacherDTO) {
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
    public String createStudent(@ModelAttribute("studentDTO") SchoolDTO studentDTO){

        Student student = studentDTO.getStudent();
        Member member = studentDTO.getMember();

        String password ="{noop}"+  member.getPassword();
        member.setPassword(password);


        schoolService.saveMember(member);
        student.setMember(member);
        schoolService.saveStudent(student);


        return "redirect:/";

    }
}
