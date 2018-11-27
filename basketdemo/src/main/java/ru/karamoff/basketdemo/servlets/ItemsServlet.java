package ru.karamoff.basketdemo.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.karamoff.basketdemo.Helpers;
import ru.karamoff.basketdemo.models.Item;
import ru.karamoff.basketdemo.repositories.ItemRepository;
import ru.karamoff.basketdemo.repositories.ItemRepositoryImpl;
import ru.karamoff.basketdemo.services.ItemService;
import ru.karamoff.basketdemo.services.ItemServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/items")
public class ItemsServlet extends HttpServlet {

    private ItemService service;
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void init() {
        DriverManagerDataSource dataSource = Helpers.getDefaultDataSource();
        ItemRepository itemRepository = new ItemRepositoryImpl(dataSource);
        this.service = new ItemServiceImpl(itemRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        List<Item> items = service.getAllItems();
        String resultJson = mapper.writeValueAsString(items);
        resp.setStatus(200);
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(resultJson);
    }
}
