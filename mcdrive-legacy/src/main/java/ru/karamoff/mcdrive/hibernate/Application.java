package ru.karamoff.mcdrive.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.karamoff.mcdrive.models.*;

public class Application {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:55432/mcdrive");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "qwerty007");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");

        configuration.addAnnotatedClass(AuthCookie.class);
        configuration.addAnnotatedClass(Foodpiece.class);
        configuration.addAnnotatedClass(Ingredient.class);
        configuration.addAnnotatedClass(Order.class);
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.show_sql", "true");

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.close();
    }
}