package org.llamagas.servicelayer.repository;

import org.llamagas.servicelayer.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsersRepository extends JpaRepository<Users, String> {

    Optional<Users> findByUsernameAndPassword(String username, String password);
    Optional<Users> findByUsername(String username);
}
