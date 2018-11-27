package ru.karamoff.basketdemo;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Helpers {
    public static DriverManagerDataSource getDefaultDataSource() {
        return getPostgresDataSource("postgres", "qwerty007", "localhost", 55432, "db_11_702");
    }

    public static DriverManagerDataSource getPostgresDataSource(String username,
                                                                String password,
                                                                String host,
                                                                Integer port,
                                                                String dbName) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl("jdbc:postgresql://" + host + ":" + port.toString() + "/" + dbName);
        return dataSource;
    }
}
