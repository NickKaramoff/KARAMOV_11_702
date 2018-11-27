package ru.karamoff.mcdrive.servlets;

import lombok.SneakyThrows;
import ru.karamoff.mcdrive.forms.FoodpieceForm;
import ru.karamoff.mcdrive.repositories.FoodpieceRepository;
import ru.karamoff.mcdrive.repositories.FoodpieceRepositoryJdbcImpl;
import ru.karamoff.mcdrive.services.FoodpieceService;
import ru.karamoff.mcdrive.services.FoodpieceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

@WebServlet("/foodpiece/add")
public class AddFoodPieceServlet extends HttpServlet {

    private FoodpieceService service;

    @Override
    @SneakyThrows
    public void init() throws ServletException {
        Class.forName("org.postgresql.Driver");
        Properties properties = new Properties();
        properties.load(new FileReader("/Users/nick/dev/KARAMOV_11_702/mcdrive/src/main/resources/ru/karamoff/application.properties"));
        Connection connection = DriverManager.getConnection(
                properties.getProperty("db.url"),
                properties.getProperty("db.user"),
                properties.getProperty("db.password")
        );
        FoodpieceRepository repository = new FoodpieceRepositoryJdbcImpl(connection);
        service = new FoodpieceServiceImpl(repository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/add-foodpiece.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        FoodpieceForm form = FoodpieceForm.builder()
                .name(req.getParameter("name"))
                .cost(Float.valueOf(req.getParameter("cost")))
                .available(req.getParameter("available") != null && req.getParameter("available").equals("on"))
                .build();

        service.addFoodpiece(form);
        resp.sendRedirect("/foodpiece/add");
    }
}
