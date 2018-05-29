package ru.karamoff;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    @Parameter(names = "-classFolder")
    private String classFolder;

    public static void main(String[] args) {
        Main main = new Main();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);
        try {
            main.run();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void run() throws Exception {
        URL url = new URL("file://" + classFolder + "//");
        URLClassLoader loader =
                URLClassLoader.newInstance(new URL[]{url},
                        getClass().getClassLoader());
        Files
                .list(Paths.get(classFolder))
                .filter(path -> !path.getFileName().toString().equals(".DS_Store"))
                .forEach(file -> {
                    try {
                        String classname = file.getFileName().toString().split("\\.")[0];

                        Class aClass = Class.forName(classname, true, loader);


                        System.out.println(aClass.getName());

                        System.out.println("= FIELDS");
                        for (Field field : aClass.getDeclaredFields())
                            System.out.println("  " + field.toString());

                        System.out.println("= CONSTRUCTORS");
                        for (Constructor constructor : aClass.getDeclaredConstructors())
                            System.out.println("  " + constructor.toString());

                        System.out.println("= METHODS");
                        for (Method method : aClass.getDeclaredMethods())
                            System.out.println("  " + method.toString());

                        System.out.println();
                    } catch (Exception e) {
                        throw new IllegalArgumentException(e);
                    }
                });
    }
}
