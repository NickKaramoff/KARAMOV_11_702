package ru.karamoff.basketdemo.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.karamoff.basketdemo.Helpers;
import ru.karamoff.basketdemo.models.Item;
import ru.karamoff.basketdemo.repositories.BasketRepository;
import ru.karamoff.basketdemo.repositories.BasketRepositoryImpl;
import ru.karamoff.basketdemo.repositories.ItemRepository;
import ru.karamoff.basketdemo.repositories.ItemRepositoryImpl;
import ru.karamoff.basketdemo.services.BasketService;
import ru.karamoff.basketdemo.services.BasketServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet("/basket")
public class BasketServlet extends HttpServlet {

    private BasketService basketService;
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void init() {
        DriverManagerDataSource dataSource = Helpers.getDefaultDataSource();
        ItemRepository itemRepository = new ItemRepositoryImpl(dataSource);
        BasketRepository basketRepository = new BasketRepositoryImpl(dataSource);
        this.basketService = new BasketServiceImpl(basketRepository, itemRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        UUID basketUuid = getBasketUuid(req, resp);
        String resultJson = mapper.writeValueAsString(basketService.getAllItemsInBasket(basketUuid));
        resp.setStatus(200);
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(resultJson);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        UUID basketUuid = getBasketUuid(req, resp);
        Item item = basketService.getItemFromBasket(
                Long.parseLong(req.getParameter("itemid")),
                basketUuid);
        basketService.addItemToBasket(item, basketUuid);

        resp.setStatus(200);
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write("{\"status\": \"OK\"}");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        UUID basketUuid = getBasketUuid(req, resp);
        Item item = basketService.getItemFromBasket(
                Long.parseLong(req.getParameter("itemid")),
                basketUuid);
        basketService.removeItemFromBasket(item, basketUuid);

        resp.setStatus(200);
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write("{\"status\": \"OK\"}");
    }

    private UUID getBasketUuid(HttpServletRequest req, HttpServletResponse res) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            cookies = new Cookie[0];
        }

        boolean basketFound = false;
        UUID basketUUID = null;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("auth_cookie")) {
                if (basketService.basketExists(UUID.fromString(cookie.getValue()))) {
                    basketUUID = UUID.fromString(cookie.getValue());
                    basketFound = true;
                }
                break;
            }
        }

        if (!basketFound) {
            basketUUID = basketService.createNewBasket();
            Cookie cookie = new Cookie("auth_cookie", basketUUID.toString());
            res.addCookie(cookie);
        }

        return basketUUID;
    }
}
