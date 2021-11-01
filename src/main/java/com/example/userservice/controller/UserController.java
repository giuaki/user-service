package com.example.userservice.controller;

import com.example.userservice.entity.Users;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public List<Users> getAll(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable("user")
    public Users findbyId(@PathVariable long id){
        System.out.println("get user by db");
        return  userRepository.findById(id).get();
    }
    @CacheEvict(value = "user", allEntries = true)
    @GetMapping("/delete")
    public void delete(){
        System.out.println("delete cache");
    }
    @PostMapping
    public Users add(@RequestBody Users users){
        return userRepository.save(users);
    }

}
