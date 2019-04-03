package ru.karamoff.mcdrive.repositories;

import ru.karamoff.mcdrive.models.AuthCookie;

public interface AuthCookieRepository extends CrudRepository<AuthCookie, Long> {
    AuthCookie getByUserId(Long userId);
    AuthCookie getByValue(String value);
}
