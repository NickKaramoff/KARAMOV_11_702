package ru.karamoff.mcdrive.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.karamoff.mcdrive.models.AuthCookie;
import ru.karamoff.mcdrive.models.User;
import ru.karamoff.mcdrive.repositories.AuthCookieRepository;
import ru.karamoff.mcdrive.repositories.UserRepository;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthCookieRepository authCookieRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthCookie authorize(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassHash())) {
                return fetchOrCreate(user);
            }
        }

        return null;
    }

    @Override
    public User getLoggedInUser(String cookieValue) {
        AuthCookie cookie = authCookieRepository.getByValue(cookieValue);
        if (cookie != null) {
            return cookie.getUser();
        }

        return null;
    }

    private AuthCookie fetchOrCreate(User user) {
        AuthCookie cookie = authCookieRepository.getByUserId(user.getId());

        if (cookie == null) {
            cookie = AuthCookie.builder()
                    .user(user)
                    .value(UUID.randomUUID())
                    .build();
            authCookieRepository.save(cookie);
        }

        return cookie;
    }
}
