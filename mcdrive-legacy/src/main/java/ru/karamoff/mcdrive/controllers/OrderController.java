package ru.karamoff.mcdrive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import ru.karamoff.mcdrive.models.User;
import ru.karamoff.mcdrive.services.OrderService;
import ru.karamoff.mcdrive.services.UserService;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/orders")
    public String displayOrders(@CookieValue(value = "auth_cookie", defaultValue = "") String cookieValue, ModelMap model) {
        if (!cookieValue.equals("")) {
            User user = userService.getLoggedInUser(cookieValue);
            if (user != null) {
                model.addAttribute("pageTitle", "Заказы");
                model.addAttribute("orders", orderService.getAllOrders());
                model.addAttribute("tabNumber", 0);

                model.addAttribute("user", user);
                return "orders";
            }
        }
        return "redirect:/login";
    }
}
