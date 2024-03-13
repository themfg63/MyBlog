package com.SpringBoot.WebBlog.controller;

import com.SpringBoot.WebBlog.dto.RegistrationDto;
import com.SpringBoot.WebBlog.entity.User;
import com.SpringBoot.WebBlog.service.Interface.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService){
        this.userService=userService;
    }

    //login sayfası isteği
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user",user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user, BindingResult result,Model model){
        User existingUser = userService.findByEmail(user.getEmail());
        if(existingUser != null){
            result.rejectValue("email",null,"Aynı e-posta kimliğine sahip bir kullanıcı zaten var");
        }
        if(result.hasErrors()){
            model.addAttribute("user",user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }
}
