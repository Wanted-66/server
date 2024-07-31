package dev.changuii.project.controller;

import dev.changuii.project.entity.UserEntity;
import dev.changuii.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final UserRepository userRepository;


    public TestController(
            @Autowired  UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/user/{email}")
    public void createUserForTest(
            @PathVariable("email") String email
    ){
        UserEntity u = UserEntity.builder().email(email).build();
        this.userRepository.save(u);
    }



}
