package ru.karamoff.mcdrive.repositories;

import ru.karamoff.mcdrive.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
