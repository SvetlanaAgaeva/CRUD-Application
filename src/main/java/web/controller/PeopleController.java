package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.DAO.UserDaoImpl;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImpl;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/")
public class PeopleController {
// Можно я не буду это удалять? Вдруг мне понадобится в других проектах?
//    @Autowired
//    private ApplicationContext context;
//
//    @PostConstruct
//    public void logBeans() {
//        for (String name : context.getBeanDefinitionNames()) {
//            System.out.println("Bean: " + name);
//        }
//    }

    private final UserService userService;

    @Autowired
    public PeopleController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String homePage() {
        return "homePage";
    }

    @GetMapping("/people")
    public String ListOfPeople(Model model) {
        model.addAttribute("people", userService.getAllUsers());
        return "list";
    }


    @GetMapping("/people/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("/people")
    public String createNewUser(@ModelAttribute("user") User user) {

        userService.saveUser(user);
        return "redirect:/people";
    }

    @GetMapping("/people/edit")
    public String editUserForm(@RequestParam int id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "error";
        }
        model.addAttribute("user", user); //
        return "edit";

    }

    @PostMapping("/people/update")
    public String updateUser(@RequestParam int id, @ModelAttribute("user") User newUser) {

        userService.updateUser(id, newUser);
        return "redirect:/people";
    }

    @GetMapping("/people/delete")
    public String deleteUser(@RequestParam int id) {
        userService.deleteUserById(id);
        return "redirect:/people";
    }
}