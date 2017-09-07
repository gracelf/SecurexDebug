package byAJ.Securex.controllers;

import byAJ.Securex.UserService;
import byAJ.Securex.models.User;
import byAJ.Securex.models.Role;
import byAJ.Securex.repositories.RoleRepo;
import byAJ.Securex.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class HomeController {


    @Autowired
    RoleRepo roleRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }


    @RequestMapping("/addnewroles")
    public @ResponseBody
    String addnewRoles()
    {
        Role newrole = new Role();
        newrole.setRole("ADMIN");
        roleRepo.save(newrole);

        Role newrole2 = new Role();
        newrole.setRole("USER");
        roleRepo.save(newrole2);

        return "new role (Admin and User) are added";
    }

    //register for accout as an user role
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model){
        User user= new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult bResult, Model model)
    {
        model.addAttribute("user", new User());

        if (bResult.hasErrors())
        {
            return "registration";
        }
        else{
            userService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created");
        }
        return "index";
    }

    //register for accout as an admin role
    @RequestMapping(value="/registerAdmin", method = RequestMethod.GET)
    public String showRegistrationformforAdmin(Model model){
        model.addAttribute("user", new User());
        return "adminregistration";
    }

    @RequestMapping(value="/registerAdmin", method = RequestMethod.POST)
    public String processRegistrationPageforAdmin(@Valid @ModelAttribute("user") User user, BindingResult bResult, Model model)
    {
        model.addAttribute("user", new User());

        if (bResult.hasErrors())
        {
            return "registration";
        }
        else{
            userService.saveAdmin(user);
            model.addAttribute("message", "Admin Account Successfully Created");
        }
        return "index";
    }


}
