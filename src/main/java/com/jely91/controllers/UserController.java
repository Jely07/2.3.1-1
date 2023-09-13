package com.jely91.controllers;

import com.jely91.dao.UserDao;
import com.jely91.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping
    public String getAllUsers(Model model){
        model.addAttribute("users",userDao.getUsers());
        return "users/all-Users";
    }
    @GetMapping("/{id}")
    public String getUser(@PathVariable int id,Model model) {
        model.addAttribute("user",userDao.getUser(id));
        return "users/getUser";
    }
    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user",new User());
        return "users/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("user")@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "users/new";
        }
        userDao.save(user);
        return "redirect:/users";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id")int id){
        model.addAttribute("user",userDao.getUser(id));
        return "users/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user")@Valid User user,BindingResult bindingResult,
                         @PathVariable("id")int id){
       if(bindingResult.hasErrors()){
           return "users/edit";
       }
        userDao.update(id,user);
        return "redirect:/users";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        userDao.delete(id);
        return "redirect:/users";
    }
}
