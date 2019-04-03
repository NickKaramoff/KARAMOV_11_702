package ru.karamoff.mcdrive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.karamoff.mcdrive.forms.UserForm;
import ru.karamoff.mcdrive.models.AuthCookie;
import ru.karamoff.mcdrive.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("pageTitle", "Вход");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginUser(UserForm userForm, HttpServletResponse resp) {
        AuthCookie cookie = userService.authorize(
                userForm.getEmail(),
                userForm.getPassword());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(HttpStatus.UNAUTHORIZED);

        if (cookie != null) {
            resp.addCookie(new Cookie("auth_cookie", cookie.getValue().toString()));
            resp.setStatus(200);
            return new ModelAndView("redirect:/orders");
        } else {
//            TODO: возврат 401
            return null;
        }
    }
}
