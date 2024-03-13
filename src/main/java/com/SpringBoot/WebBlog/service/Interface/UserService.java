package com.SpringBoot.WebBlog.service.Interface;

import com.SpringBoot.WebBlog.dto.RegistrationDto;
import com.SpringBoot.WebBlog.entity.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    User findByEmail(String email);
}
