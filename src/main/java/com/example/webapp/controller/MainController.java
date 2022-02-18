package com.example.webapp.controller;

import com.example.webapp.model.User;
import com.example.webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/")
    public String main(Map<String, Object> model) {
        List<User> userInfo = userRepository.findAll();
        model.put("user", userInfo );

        return "main";
    }


    @PostMapping("/block/{id}")
    public String BlockUser(@PathVariable(value="id") long id , Model model)
    {
    User user=userRepository.findAllById(id);
    user.setActive(false);
    userRepository.save(user);
    return "redirect:/";
    }

    @PostMapping("/unblock/{id}")
    public String UnBlockUser(@PathVariable(value="id") long id , Model model)
    {
        User user=userRepository.findAllById(id);
        user.setActive(true);
        userRepository.save(user);
        return "redirect:/";
    }


    @PostMapping("/delete/{id}")
    public String DeleteUser(@PathVariable(value="id") long id , Model model)
    {
        User user=userRepository.findAllById(id);
        userRepository.delete(user);
        return "redirect:/";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        List<User> userInfo;

        if (filter != null && !filter.isEmpty()) {
            userInfo = userRepository.findAllByUsername(filter);
        } else {
            userInfo =userRepository.findAll();
        }

        model.put("user", userInfo);

        return "main";
    }


}
