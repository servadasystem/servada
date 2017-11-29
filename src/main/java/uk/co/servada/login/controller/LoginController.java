package uk.co.servada.login.controller;

/*import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;*/
/*
@Controller
public class LoginController {

	@RequestMapping("login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		return "login/login";
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		return "login/logout";
	}
}*/



import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.co.servada.login.entity.*;
import uk.co.servada.login.service.UserService;


@Controller
@RequestMapping("/")
@ComponentScan("uk.co.servada")
public class LoginController {

    @Autowired
    UserService service;
      
    @Autowired
    MessageSource messageSource;
      
     // This method will list all existing users.   
    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public String listusers(ModelMap model) {
        List users = service.findAllUser();
        model.addAttribute("users", users);
        return "allusers";
    }
    // This method will provide the medium to add a new user.    
    @RequestMapping(value = { "/new" }, method = RequestMethod.GET)
    public String newuser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        return "registration";

    }

     // This method will be called on form submission, handling POST request for
     // saving user in database. It also validates the user input    
    @RequestMapping(value = { "/new" }, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result,
            ModelMap model) {
        if (result.hasErrors()) {
            return "registration";
        }

         // Preferred way to achieve uniqueness of field [loginID] should be implementing custom @Unique annotation
         // and applying it on field [loginID] of Model class [User].Below mentioned peace of code [if block] is
          // to demonstrate that you can fill custom errors outside the validation
         // framework as well while still using internationalised messages.

        if(!service.isLoginIDUnique(user.getId(), user.getLoginID())){
            FieldError loginIDError =new FieldError("user","loginID",messageSource.getMessage("non.unique.loginID", new String[]{user.getLoginID()}, Locale.getDefault()));
            result.addError(loginIDError);
            return "registration";
        }

        service.saveUser(user);
        model.addAttribute("success", "user " + user.getLoginID() + " registered successfully");
        return "success";
    }

     // This method will provide the medium to update an existing user.    
    @RequestMapping(value = { "/edit-{loginID}-user" }, method = RequestMethod.GET)
    public String edituser(@PathVariable String loginID, ModelMap model) {

        User user = service.findUserByLoginID(loginID);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "registration";
    }

     // This method will be called on form submission, handling POST request for
     // updating user in database. It also validates the user input
    @RequestMapping(value = { "/edit-{loginID}-user" }, method = RequestMethod.POST)
    public String updateuser(@Valid User user, BindingResult result,
            ModelMap model, @PathVariable String loginID) {
        if (result.hasErrors()) {
            return "registration";
        }
        if(!service.isLoginIDUnique(user.getId(), user.getLoginID())){
            FieldError loginIDError =new FieldError("user","loginID",messageSource.getMessage("non.unique.loginID", new String[]{user.getLoginID()}, Locale.getDefault()));
            result.addError(loginIDError);
            return "registration";
        }
        service.updateUser(user);
        model.addAttribute("success", "user " + user.getLoginID()  + " updated successfully");
        return "success";
    }

     // This method will delete an user by it's loginID value.    
    @RequestMapping(value = { "/delete-{loginID}-user" }, method = RequestMethod.GET)
    public String deleteuser(@PathVariable String loginID) {
        service.deleteUserByLoginID(loginID);
        return "redirect:/list";
    }
}
