package com.SpringBoot.WebBlog.service.Impl;

import com.SpringBoot.WebBlog.dto.RegistrationDto;
import com.SpringBoot.WebBlog.entity.Role;
import com.SpringBoot.WebBlog.entity.User;
import com.SpringBoot.WebBlog.repository.RoleRepository;
import com.SpringBoot.WebBlog.repository.UserRepository;
import com.SpringBoot.WebBlog.service.Interface.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,RoleRepository roleRepository,PasswordEncoder passwordEncoder){
        this.passwordEncoder=passwordEncoder;
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto){
        User user = new User();

        user.setName(registrationDto.getFirstName()+registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        Role role = roleRepository.findByName("RO1E_GUEST");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
