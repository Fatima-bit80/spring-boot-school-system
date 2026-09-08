package com.example.schoolManagement.controller;

import com.example.schoolManagement.dto.SchoolDTO;
import com.example.schoolManagement.entity.Member;
import com.example.schoolManagement.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Controller
public class MainController {

    SchoolService service;
    @Autowired
    public MainController(SchoolService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String dashboard(Authentication authentication,Model model) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {

        // get email
        String email = authentication.getName();

        // get member form email
        Member member =  service.findMemberByEmail(email);


        // get member's role
        String role = member.getRole().substring(5); //e,g: STUDENT
        String roleFormatted = role.substring(0,1).toUpperCase()+role.substring(1).toLowerCase(); // e,g: Student

        // get the user (student/teacher/admin)
       Object user = getUser(member, roleFormatted);

       // add a model attribute that holds the user's dto
       addUserToModel(user,roleFormatted,model,member);


        return role.toLowerCase()+"/"+role.toLowerCase()+"-dashboard";

    }

    private void addUserToModel(Object user, String roleFormatted, Model model, Member member) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String setter = "set"+roleFormatted;

        SchoolDTO dto = new SchoolDTO(member);

        Class<?>dtoClass = dto.getClass();
        Class cls=Class.forName( "com.example.school_management.entity."+ roleFormatted);
        Method setterMethod = dtoClass.getMethod(setter,cls);

        setterMethod.invoke(dto,user);

        model.addAttribute("schoolDTO",dto);
    }

    private Object getUser(Member member, String roleFormatted) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String getter = "get"+roleFormatted;

        Class<? >objectClass = member.getClass();
        Method getterMethod = objectClass.getMethod(getter);
        Object o = getterMethod.invoke(member);

        return o;
    }



}
