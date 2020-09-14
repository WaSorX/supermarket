package com.utrechtfour.supermarket.controller.rest;

import com.utrechtfour.supermarket.model.user.ApplicationUser;
import com.utrechtfour.supermarket.model.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private ApplicationUserRepository applicationUserRepository;

    private BCryptPasswordEncoder bcryptPasswordEncoder;


    public UserController(ApplicationUserRepository applicationUserRepository, BCryptPasswordEncoder bcryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bcryptPasswordEncoder = bcryptPasswordEncoder;
    }

    @PostMapping("sign-up")
    public void signup(@RequestBody @Valid ApplicationUser user){

        user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }
}
