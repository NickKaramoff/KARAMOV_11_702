package ru.karamoff.mcdrive.services;

import ru.karamoff.mcdrive.models.AuthCookie;
import ru.karamoff.mcdrive.models.User;

public interface UserService {
    AuthCookie authorize(String email, String password);
    User getLoggedInUser(String cookieValue);
}
